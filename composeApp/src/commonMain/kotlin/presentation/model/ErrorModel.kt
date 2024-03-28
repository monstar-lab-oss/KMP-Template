package presentation.model

import error.ApiErrorException
import io.ktor.client.network.sockets.SocketTimeoutException

sealed class ErrorModel(open val message: String) {
    data class ApiError(val exception: ApiErrorException, override val message: String) :
        ErrorModel(message)

    sealed class Connection(override val message: String) : ErrorModel(message) {
        data object Timeout : Connection("Connection timed out")
    }

    data class Unknown(val throwable: Throwable, override val message: String) : ErrorModel(message)
}

fun Throwable.toError(): ErrorModel {
    return when (this) {
        is ApiErrorException -> {
            ErrorModel.ApiError(this, "API error: ${this.message}")
        }

        is SocketTimeoutException -> ErrorModel.Connection.Timeout
        else -> ErrorModel.Unknown(this, "Unknown error: ${this.message}")
    }
}
