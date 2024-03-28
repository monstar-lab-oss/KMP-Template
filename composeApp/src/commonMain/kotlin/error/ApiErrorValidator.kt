package error

import io.ktor.client.statement.HttpResponse

interface ApiErrorValidator {
    fun validateResponse(response: HttpResponse)
}
