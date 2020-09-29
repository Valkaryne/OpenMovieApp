package com.epam.valkaryne.openmovieapp.data.di

import com.epam.valkaryne.openmovieapp.data.api.retrofit.OpenMovieApiService
import com.epam.valkaryne.openmovieapp.data.repository.OpenMovieRepositoryImpl
import com.epam.valkaryne.openmovieapp.data.repository.QueryRepositoryImpl
import com.epam.valkaryne.openmovieapp.domain.OpenMovieRepository
import com.epam.valkaryne.openmovieapp.domain.QueryRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "http://www.omdbapi.com/"

val retrofitModule = module {
    single { provideRetrofitInstance() }
}

private fun provideRetrofitInstance(): Retrofit = Retrofit.Builder()
    .baseUrl(URL_BASE)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

val openMovieApiModule = module {
    factory { provideOpenMovieApiService(retrofit = get()) }
    single<OpenMovieRepository> { OpenMovieRepositoryImpl(service = get()) }
}

private fun provideOpenMovieApiService(retrofit: Retrofit): OpenMovieApiService =
    retrofit.create(OpenMovieApiService::class.java)

val queryHistoryModule = module {
    single<QueryRepository> { QueryRepositoryImpl(context = get()) }
}
