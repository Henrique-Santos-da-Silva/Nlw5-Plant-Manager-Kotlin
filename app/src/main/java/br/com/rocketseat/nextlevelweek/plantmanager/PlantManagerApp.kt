package br.com.rocketseat.nextlevelweek.plantmanager

import android.app.Application
import br.com.rocketseat.nextlevelweek.plantmanager.di.plantManagerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PlantManagerApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@PlantManagerApp)

            modules(plantManagerModule)
        }
    }

}