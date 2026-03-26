package kmiemart.consult.app.di

import kmiemart.consult.app.ui.viewmodel.BookingViewModel
import kmiemart.consult.app.ui.viewmodel.CheckoutViewModel
import kmiemart.consult.app.ui.viewmodel.KMSTMOnboardingVM
import kmiemart.consult.app.ui.viewmodel.ServiceDetailsViewModel
import kmiemart.consult.app.ui.viewmodel.ServiceViewModel
import kmiemart.consult.app.ui.viewmodel.KMSTMSplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        KMSTMSplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        KMSTMOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ServiceViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        ServiceDetailsViewModel(
            serviceRepository = get()
        )
    }

    viewModel {
        BookingViewModel(
            bookingRepository = get(),
            serviceRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            bookingRepository = get(),
        )
    }
}