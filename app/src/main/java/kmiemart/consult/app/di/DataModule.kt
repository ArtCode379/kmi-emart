package kmiemart.consult.app.di

import kmiemart.consult.app.data.repository.BookingRepository
import kmiemart.consult.app.data.repository.KMSTMOnboardingRepo
import kmiemart.consult.app.data.repository.ServiceRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        KMSTMOnboardingRepo(
            kmstmOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ServiceRepository() }

    single{
        BookingRepository(
            bookingDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}