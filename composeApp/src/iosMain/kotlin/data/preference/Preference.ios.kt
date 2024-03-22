package data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

/**
 * Platform-specific function for creating a DataStore instance.
 * Creates a DataStore instance for iOS platform.
 *
 * @return DataStore<Preferences> instance.
 */

internal actual fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        // Retrieves the document directory path for iOS
        val docDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null
        )

        // Constructs the full file path for the DataStore
        requireNotNull(docDirectory).path + "/$DATA_STORE_FILE_NAME"
    }
)