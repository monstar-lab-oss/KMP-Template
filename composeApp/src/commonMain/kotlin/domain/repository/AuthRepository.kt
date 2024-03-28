package domain.repository

import data.network.requests.LoginRequest
import data.network.responses.AuthResponse

interface AuthRepository {
    suspend fun authenticate(loginRequest: LoginRequest): AuthResponse
}
