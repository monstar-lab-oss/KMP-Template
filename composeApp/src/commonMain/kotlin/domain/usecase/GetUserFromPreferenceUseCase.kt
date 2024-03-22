package domain.usecase

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import data.preference.SingleDataStorePreferences
import kotlinx.serialization.Serializable

@Serializable
data class User(val name: String, val email: String)
class GetUserFromPreferenceUseCase(dataStore: DataStore<Preferences>) :
    SingleDataStorePreferences<User>(dataStore, User.serializer())