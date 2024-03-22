package data.preference


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.applicationContext

/**
 * Platform-specific function for creating a DataStore instance.
 * Creates a DataStore instance for Android platform.
 *
 * @return DataStore<Preferences> instance.
 */

internal actual fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = { applicationContext.filesDir.resolve(DATA_STORE_FILE_NAME).absolutePath }
)