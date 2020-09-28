package com.epam.valkaryne.openmovieapp;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService;
import com.epam.valkaryne.openmovieapp.data.repository.OpenMovieRepositoryImpl;
import com.epam.valkaryne.openmovieapp.data.repository.QueryRepositoryImpl;
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository;
import com.epam.valkaryne.openmovieapp.domain.QueryRepository;
import com.epam.valkaryne.openmovieapp.domain.usecase.ClearQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.GetQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.SaveQueryHistoryUseCase;
import com.epam.valkaryne.openmovieapp.domain.usecase.SearchMoviesUseCase;
import com.epam.valkaryne.openmovieapp.vm.ViewModelFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Injection {

    private final static String URL_BASE = "http://www.omdbapi.com/";

    public static ViewModelProvider.Factory provideViewModelFactory(Context context) {
        return new ViewModelFactory(
                provideSearchMoviesUseCase(),
                provideGetQueryHistoryUseCase(context),
                provideSaveQueryHistoryUseCase(context),
                provideClearQueryHistoryUseCase(context)
        );
    }

    private static SearchMoviesUseCase provideSearchMoviesUseCase() {
        return new SearchMoviesUseCase(provideOpenMovieRepository());
    }

    private static GetQueryHistoryUseCase provideGetQueryHistoryUseCase(Context context) {
        return new GetQueryHistoryUseCase(provideQueryRepository(context));
    }

    private static SaveQueryHistoryUseCase provideSaveQueryHistoryUseCase(Context context) {
        return new SaveQueryHistoryUseCase(provideQueryRepository(context));
    }

    private static ClearQueryHistoryUseCase provideClearQueryHistoryUseCase(Context context) {
        return new ClearQueryHistoryUseCase(provideQueryRepository(context));
    }

    private static OpenMovieRepository provideOpenMovieRepository() {
        return new OpenMovieRepositoryImpl(provideService(
                provideRetrofitInstance())
        );
    }

    private static QueryRepository provideQueryRepository(Context context) {
        return new QueryRepositoryImpl(context);
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
