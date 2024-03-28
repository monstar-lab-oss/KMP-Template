package presentation.feature.login

/**
 * UI State that represents LoginScreen
 **/
data class LoginState(
    val email: String = "kminchelle",
    val password: String = "0lelplR",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginButtonEnabled: Boolean = false,
    val isLoggedIn: Boolean = false,
)

/**
 * Login Actions emitted from the UI Layer
 * passed to the viewmodel to handle
 **/
sealed class LoginIntent {
    data class OnPasswordChange(val pass: String) : LoginIntent()
    data class OnEmailChange(val email: String) : LoginIntent()
    data object Login : LoginIntent()
}
