package data.repository

import data.network.ApiService
import data.network.requests.LoginRequest
import data.network.responses.AuthResponse
import domain.repository.AuthRepository
import io.ktor.client.call.body

class AuthRepositoryImpl(private val apiService: ApiService) : AuthRepository {
    override suspend fun authenticate(loginRequest: LoginRequest): AuthResponse {
        return apiService.authenticate(loginRequest).body<AuthResponse>()
    }
}
