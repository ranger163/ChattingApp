package me.inassar.demos.socialapp

import android.app.Application
import me.inassar.demos.socialapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                appModule,
                cacheModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }
    }
}