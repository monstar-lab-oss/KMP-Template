package di

import data.network.errorhandling.validateResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module
import kotlinx.serialization.json.Json

object NetworkModule {
    private const val BASE_URL = "dummyjson.com"
    private const val TIMEOUT_DURATION: Long = 60_000

    val networkClient = module {
        single {
            HttpClient {
                expectSuccess = false
                HttpResponseValidator {
                    handleResponseExceptionWithRequest { exception, request ->
                        val clientException = exception as? ClientRequestException
                            ?: return@handleResponseExceptionWithRequest
                        val exceptionResponse = clientException.response
                        validateResponse(exceptionResponse)
                    }
                }
                defaultRequest {
                    url {
                        protocol = URLProtocol.HTTPS
                        host = BASE_URL
                    }
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = TIMEOUT_DURATION
                    connectTimeoutMillis = TIMEOUT_DURATION
                    socketTimeoutMillis = TIMEOUT_DURATION
                }
                install(Logging) {
                    logger = Logger.SIMPLE
                    level = LogLevel.ALL
                }
                install(ContentNegotiation) {
                    json(Json { isLenient = true; ignoreUnknownKeys = true })
                }
            }
        }
    }
}
