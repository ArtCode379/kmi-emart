package kmiemart.consult.app.di

import kmiemart.consult.app.data.datastore.KMSTMOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { KMSTMOnboardingPrefs(androidContext()) }
}