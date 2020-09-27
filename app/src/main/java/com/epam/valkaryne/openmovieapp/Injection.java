package com.epam.valkaryne.openmovieapp;

import androidx.lifecycle.ViewModelProvider;

import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService;
import com.epam.valkaryne.openmovieapp.data.repository.OpenMovieRepositoryImpl;
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository;
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase;
import com.epam.valkaryne.openmovieapp.vm.ViewModelFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Injection {

    private final static String URL_BASE = "http://www.omdbapi.com/";

    public static ViewModelProvider.Factory provideViewModelFactory() {
        return new ViewModelFactory(provideUseCase());
    }

    private static SearchMoviesUseCase provideUseCase() {
        return new SearchMoviesUseCase(provideRepository());
    }

    private static OpenMovieRepository provideRepository() {
        return new OpenMovieRepositoryImpl(provideService(
                provideRetrofitInstance())
        );
    }

    private static OpenMovieApiService provideService(Retrofit retrofit) {
        return retrofit.create(OpenMovieApiService.class);
    }

    private static Retrofit provideRetrofitInstance() {

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .build();

        return new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
