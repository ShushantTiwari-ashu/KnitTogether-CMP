package dev.shushant.network

import dev.shushant.network.functional.Either
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.RequestTimeout
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseAPI(val httpHelper: HTTPHelper) {

    fun URLBuilder.apiPath(
        endPoint: String,
        url: String = "",
        urlBuilder: URLBuilder.() -> Unit
    ) {
        takeFrom(url)
        if (endPoint.isNotBlank()) {
            encodedPath = endPoint
        }
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
        } catch (e: CancellationException) {
            Either.Failure(NetworkFailure(e))
        }
        catch (e:Exception){
            Either.Failure(NetworkFailure(e))
        }
    }

    suspend inline fun <reified T, reified R> doPost(
        endPoint: String,
        url: String = "",
        requestBody: R,
        noinline urlBuilder: URLBuilder.() -> Unit = {}
    ): Either<T, NetworkFailure> {
        return try {
            val result = httpHelper.doPost<T, R>(requestBody) {
                apply {
                    apiPath(endPoint = endPoint, urlBuilder = urlBuilder, url = url)
                }
            }
            Either.Success(result)
        } catch (e: ResponseException) {
            when (e.response.status) {
                NotFound -> {
                    Either.Failure(NetworkFailure(Exception("Invalid credentials!")))
                }

                BadRequest -> {
                    Either.Failure(NetworkFailure(Exception("Bad request!")))
                }

                RequestTimeout -> {
                    Either.Failure(NetworkFailure(Exception("Request Timeout!")))
                }

                else -> {
                    Either.Failure(NetworkFailure(e))
                }
            }
        }
    }
}