package presentation.feature.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.components.appbutton.AppButton
import presentation.components.apptextfield.AppTextField
import presentation.feature.home.HomeScreen
import presentation.theme.Theme

object LoginScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = LoginViewModel()
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.state.collectAsState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Login",
                            fontWeight = FontWeight.Black,
                            style = MaterialTheme.typography.h5,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    },
                    backgroundColor = Theme.colors.primary,
                    contentColor = Theme.colors.onPrimary,
                )
            },
        ) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .padding(Theme.dimensions.big1)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                AppTextField(
                    value = state.email,
                    onValueChange = { text ->
                        viewModel.handleIntent(LoginIntent.OnEmailChange(email = text))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Username") },
                )

                Spacer(modifier = Modifier.size(Theme.dimensions.medium3))

                AppTextField(
                    value = state.password,
                    onValueChange = { text ->
                        viewModel.handleIntent(LoginIntent.OnPasswordChange(text))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    placeholder = { Text(text = "Password") },
                )

                Spacer(modifier = Modifier.size(Theme.dimensions.medium3))

                AppButton(
                    text = "Login",
                    onClick = {
                        viewModel.handleIntent(LoginIntent.Login)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = state.isLoading,
                )

                if (state.isLoggedIn) navigator.replaceAll(HomeScreen)
            }
        }
    }
}
