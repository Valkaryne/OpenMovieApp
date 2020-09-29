package com.epam.valkaryne.openmovieapp

import android.app.Application
import com.epam.valkaryne.openmovieapp.data.di.openMovieApiModule
import com.epam.valkaryne.openmovieapp.data.di.queryHistoryModule
import com.epam.valkaryne.openmovieapp.data.di.retrofitModule
import com.epam.valkaryne.openmovieapp.di.movieListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OpenMovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@OpenMovieApplication)
            modules(listOf(
                retrofitModule,
                openMovieApiModule,
                queryHistoryModule,
                movieListModule
            ))
        }
    }
}