package error

import io.ktor.utils.io.errors.IOException

class ApiErrorException(
    val code: Int,
    val displayableMessage: String,
    val loggableMessage: String,
) : IOException(displayableMessage)
