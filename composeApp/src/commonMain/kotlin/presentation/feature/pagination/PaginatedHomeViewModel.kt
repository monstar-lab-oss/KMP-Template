package presentation.feature.pagination

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.paging.cachedIn
import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import domain.usecase.GetPaginatedProductsUseCase
import domain.usecase.GetUserFromPreferenceUseCase
import domain.usecase.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * ViewModel for managing data related to the paginated home screen.
 * Uses [GetPaginatedProductsUseCase] to fetch paginated products data.
 * Utilizes DataStore for storing and retrieving a simple count value.
 */
class PaginatedHomeViewModel : KMMViewModel(), KoinComponent {

    // Common methods for User management
    private val userUseCase: GetUserFromPreferenceUseCase by inject()

    // Dependency injection for GetPaginatedProductsUseCase
    private val getPaginatedProductsUseCase: GetPaginatedProductsUseCase by inject()

    // Flow of paginated product list obtained from the use case
    val productList = getPaginatedProductsUseCase().cachedIn(viewModelScope.coroutineScope)
    private val _user = MutableStateFlow<User?>(null)

    val user = _user.asStateFlow()

    // Injected DataStore instance for storing simple data
    private val simpleDataStore: DataStore<Preferences> by inject()

    // Key for storing an integer value in the DataStore
    private val counterKey = intPreferencesKey("test_counter_key")

    // Initialize ViewModel and increment the count value
    init {
        incrementCount()
        getUser()
    }

    /**
     * Retrieves the count value stored in the DataStore.
     * If no value is found, returns 0.
     */
    suspend fun count(): Int = simpleDataStore.data.map { it[counterKey] ?: 0 }.first()

    /**
     * Increments the count value stored in the DataStore.
     */
    private fun incrementCount() = viewModelScope.coroutineScope.launch {
        // Retrieve the current count value and increment by 1
        val currentCount = count() + 1
        // Update the count value in the DataStore
        simpleDataStore.edit { preferences ->
            preferences[counterKey] = currentCount
        }
    }

    private fun getUser() = viewModelScope.coroutineScope.launch {
        _user.emit(userUseCase.get())
    }

    fun saveUser(name: String, email: String) = viewModelScope.coroutineScope.launch {
        val user = User(name, email)
        userUseCase.add(user)
    }
}
