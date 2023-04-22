package dev.shushant.network

import dev.shushant.network.functional.Either
import io.ktor.client.utils.*
import io.ktor.http.*

abstract class BaseAPI {
    abstract val baseUrl: String
    val httpHelper = HTTPHelper()

    fun URLBuilder.apiPath(
        endPoint: String,
        url: String = "",
        urlBuilder: URLBuilder.() -> Unit
    ) {
        takeFrom(url.ifEmpty { baseUrl })
        encodedPath = endPoint
        apply(urlBuilder)
    }

    suspend inline fun <reified T> doGet(
        endPoint: String, url: String = "", noinline urlBuilder: URLBuilder.() -> Unit = {}
    ): Either<T, NetworkFailure> {
        return try {
            val result = httpHelper.doGet<T> {
                apply {
                    apiPath(endPoint = endPoint, urlBuilder = urlBuilder, url = url)
                }
            }
            Either.Success(result)
        } catch (e: Exception) {
            Either.Failure(NetworkFailure(e))
        }
    }

    suspend inline fun <reified T> doGetWithByteArray(
        endPoint: String, url: String = "", noinline urlBuilder: URLBuilder.() -> Unit = {}
    ): Either<ByteArray, NetworkFailure> {
        return try {
            val result = httpHelper.doGetWithByteArray<T> {
                apply {
                    apiPath(endPoint = endPoint, urlBuilder = urlBuilder, url = url)
                }
            }
            Either.Success(result)
        } catch (e: Exception) {
            Either.Failure(NetworkFailure(e))
        }
    }

    suspend inline fun <reified T> doPost(
        endPoint: String,
        url: String = "",
        requestBody: Any = EmptyContent,
        noinline urlBuilder: URLBuilder.() -> Unit = {}
    ): Either<T, NetworkFailure> {
        return try {
            val result = httpHelper.doPost<T>(requestBody) {
                apply {
                    apiPath(endPoint = endPoint, urlBuilder = urlBuilder, url = url)
                }
            }
            Either.Success(result)
        } catch (e: Exception) {
            Either.Failure(NetworkFailure(e))
        }
    }
}