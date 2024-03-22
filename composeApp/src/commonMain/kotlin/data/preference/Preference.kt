package data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okio.Path.Companion.toPath

internal const val DATA_STORE_FILE_NAME = "theme.preferences_pb"

/**
 * Creates a DataStore instance for storing and accessing preferences.
 * Uses the PreferenceDataStoreFactory to create the DataStore.
 *
 * @param producePath Function that produces the path where the DataStore file will be stored.
 * @return DataStore<Preferences> instance.
 */

fun createDataStore(producePath: () -> String): DataStore<Preferences> {
    return PreferenceDataStoreFactory.createWithPath(
        corruptionHandler = null,
        migrations = emptyList(),
        scope = CoroutineScope(Dispatchers.Default + SupervisorJob()), // Coroutine scope for IO operations
        produceFile = { producePath().toPath() }, // Produces the file path for the DataStore
    )
}

internal expect fun createDataStore(): DataStore<Preferences>