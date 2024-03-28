package data.network.errorhandling

import error.ApiErrorException
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

fun validateResponse(response: HttpResponse) {
    val statusCode = response.status.value
    val displayableMessage = response.status.toDisplayableMessage()
    val loggableMessage = "Error code: $statusCode, message: $displayableMessage"
    throw ApiErrorException(statusCode, displayableMessage, loggableMessage)
}

fun HttpStatusCode.toDisplayableMessage(): String {
    return when (this) {
        HttpStatusCode.Forbidden -> "Access denied"
        HttpStatusCode.BadRequest -> "Bad request"
        HttpStatusCode.Unauthorized -> "Unauthorized"
        HttpStatusCode.NotFound -> "Resource not found"
        HttpStatusCode.InternalServerError -> "Internal server error"
        HttpStatusCode.ServiceUnavailable -> "Service unavailable"
        HttpStatusCode.RequestTimeout -> "The request timed out"
        else -> "Unexpected status code: $value"
    }
}
