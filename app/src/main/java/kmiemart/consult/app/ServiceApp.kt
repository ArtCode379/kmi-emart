package kmiemart.consult.app

import android.app.Application
import kmiemart.consult.app.di.dataModule
import kmiemart.consult.app.di.dispatcherModule
import kmiemart.consult.app.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ServiceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@ServiceApp)
            modules(appModules)
        }
    }
}