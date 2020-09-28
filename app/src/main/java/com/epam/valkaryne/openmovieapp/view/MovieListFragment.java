package com.epam.valkaryne.openmovieapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.epam.valkaryne.openmovieapp.Injection;
import com.epam.valkaryne.openmovieapp.R;
import com.epam.valkaryne.openmovieapp.core.model.QueryModel;
import com.epam.valkaryne.openmovieapp.databinding.FragmentMovieListBinding;
import com.epam.valkaryne.openmovieapp.view.adapter.MoviesAdapter;
import com.epam.valkaryne.openmovieapp.vm.SearchMoviesViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListFragment extends Fragment {

    private final String LAST_SEARCH_QUERY = "last_search_query";

    private CompositeDisposable disposable = new CompositeDisposable();

    private SearchMoviesViewModel viewModel;
    private MoviesAdapter adapter = new MoviesAdapter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMovieListBinding binding = FragmentMovieListBinding.inflate(inflater, container, false);
        if (getContext() == null) {
            return binding.getRoot();
        }

        viewModel = new ViewModelProvider(this, Injection.provideViewModelFactory(getContext()))
                .get(SearchMoviesViewModel.class);

        binding.list.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        binding.list.setAdapter(adapter);

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        QueryModel restoredQuery = savedInstanceState != null ? savedInstanceState.getParcelable(LAST_SEARCH_QUERY) : null;
        if (restoredQuery != null) {
            searchMovies(restoredQuery);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_movies) {
            SearchDialog.show(
                    getParentFragmentManager(),
                    new SearchDialog.OnDialogInteraction() {
                        @Override
                        public void performSearch(QueryModel queryModel) {
                            searchMovies(queryModel);
                            viewModel.saveQueryHistory(queryModel);
                        }

                        @Override
                        public void clearHistory() {
                            viewModel.clearQueryHistory();
                        }
                    },
                    viewModel.getQueryHistory()
            );
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void searchMovies(QueryModel queryModel) {
        disposable.add(
                viewModel.searchMovies(queryModel)
                        .subscribe(movieDataModelPagingData ->
                                adapter.submitData(getLifecycle(), movieDataModelPagingData))
        );
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (viewModel.getLastQuery() != null) {
            outState.putParcelable(LAST_SEARCH_QUERY, viewModel.getLastQuery());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }
}