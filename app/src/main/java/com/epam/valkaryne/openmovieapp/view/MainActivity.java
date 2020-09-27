package com.epam.valkaryne.openmovieapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingData;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.epam.valkaryne.openmovieapp.Injection;
import com.epam.valkaryne.openmovieapp.data.api.model.MovieDataModel;
import com.epam.valkaryne.openmovieapp.databinding.ActivityMainBinding;
import com.epam.valkaryne.openmovieapp.view.adapter.MoviesAdapter;
import com.epam.valkaryne.openmovieapp.vm.SearchMoviesViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private ActivityMainBinding mBinding;
    private SearchMoviesViewModel mViewModel;
    private MoviesAdapter mAdapter = new MoviesAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mViewModel = new ViewModelProvider(this, Injection.provideViewModelFactory())
                .get(SearchMoviesViewModel.class);

        mBinding.list.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mBinding.list.setAdapter(mAdapter);

        mDisposable.add(
                mViewModel.searchMovies("gam*")
                        .subscribe(movieDataModelPagingData
                                -> mAdapter.submitData(getLifecycle(), movieDataModelPagingData))
        );
    }
}