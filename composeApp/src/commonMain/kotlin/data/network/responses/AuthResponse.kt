package data.network.responses

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val id: Int,
    val token: String,
)
