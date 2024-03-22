package core

import android.content.Context
import androidx.startup.Initializer

/**
 * Initializes and provides the application context to other components.
 */
internal lateinit var applicationContext: Context
    private set

/**
 * Initializes the application context and marks completion of initialization.
 */
data object ContextProviderInitializer

/**
 * Provides the application context during initialization.
 */
class ContextProvider : Initializer<ContextProviderInitializer> {

    /**
     * Sets the application context and completes initialization.
     */
    override fun create(context: Context): ContextProviderInitializer {
        applicationContext = context.applicationContext
        return ContextProviderInitializer
    }

    /**
     * Specifies dependencies, which in this case is empty.
     */
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
