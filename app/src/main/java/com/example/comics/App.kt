package com.example.comics

import android.app.Application
import com.example.comics.di.dataModule
import com.example.comics.di.retrofitModule
import com.example.comics.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        configKoin()
    }

    private fun configKoin(){
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                dataModule,
                viewModelModule,
                retrofitModule
            )
        }
    }
}