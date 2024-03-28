package domain.usecase

import data.network.requests.LoginRequest
import data.network.responses.AuthResponse
import domain.repository.AuthRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class LoginUseCaseTest {

    private lateinit var sut: LoginUseCase
    private val mockAuthRepository = object : AuthRepository {
        override suspend fun authenticate(loginRequest: LoginRequest): AuthResponse {
            return AuthResponse(id = 0, token = "token")
        }
    }

    @BeforeTest
    fun setUp() {
        sut = LoginUseCase(mockAuthRepository)
    }

    @Test
    fun loginUseCase_ValidCredentials_ReturnsAuthResponse() = runTest {
        // Arrange
        val loginRequest = LoginRequest("test", "password")
        val expectedResult = AuthResponse(id = 0, token = "token")

        // Act
        val result = sut(loginRequest)

        // Assert
        assertEquals(expectedResult, result.getOrNull())
    }
}
