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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.epam.valkaryne.openmovieapp.Injection;
import com.epam.valkaryne.openmovieapp.R;
import com.epam.valkaryne.openmovieapp.databinding.FragmentMovieListBinding;
import com.epam.valkaryne.openmovieapp.view.adapter.MoviesAdapter;
import com.epam.valkaryne.openmovieapp.vm.SearchMoviesViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class MovieListFragment extends Fragment {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMovieListBinding binding = FragmentMovieListBinding.inflate(inflater, container, false);
        if (getContext() == null) {
            return binding.getRoot();
        }

        SearchMoviesViewModel viewModel = new ViewModelProvider(this, Injection.provideViewModelFactory())
                .get(SearchMoviesViewModel.class);

        MoviesAdapter adapter = new MoviesAdapter();

        binding.list.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        binding.list.setAdapter(adapter);

        disposable.add(
                viewModel.searchMovies("gam*")
                        .subscribe(movieDataModelPagingData
                                -> adapter.submitData(getLifecycle(), movieDataModelPagingData))
        );

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_movie_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_movies) {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_movieListFragment_to_searchDialog);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}