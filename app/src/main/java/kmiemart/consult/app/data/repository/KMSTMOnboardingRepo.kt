package kmiemart.consult.app.data.repository

import kmiemart.consult.app.data.datastore.KMSTMOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class KMSTMOnboardingRepo(
    private val kmstmOnboardingStoreManager: KMSTMOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return kmstmOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            kmstmOnboardingStoreManager.setOnboardedState(state)
        }
    }
}