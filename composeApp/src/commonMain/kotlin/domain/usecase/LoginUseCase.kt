package domain.usecase

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import data.network.requests.LoginRequest
import domain.repository.AuthRepository

class LoginUseCase(
    private val authRepository: AuthRepository,
) {
    @NativeCoroutines
    suspend operator fun invoke(loginRequest: LoginRequest) = runCatching {
        authRepository.authenticate(loginRequest)
    }
}
