package dev.shushant.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.retry
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.readBytes
import io.ktor.client.utils.EmptyContent
import io.ktor.http.ContentType
import io.ktor.http.URLBuilder
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HTTPHelper(val client: HttpClient) {

    suspend inline fun <reified T> doGet(
        urlBuilder: URLBuilder.() -> Unit
    ): T {
        return client.get {
            url.apply(urlBuilder)
        }.body()
    }

    suspend inline fun <reified T> doGetWithByteArray(
        crossinline urlBuilder: URLBuilder.() -> Unit
    ): ByteArray {
        return withContext(Dispatchers.Default){
            client.get {
                url.apply(urlBuilder)
            }.readBytes()
        }
    }

    suspend inline fun <reified T,reified R> doPost(
        requestBody: R,
        urlBuilder: URLBuilder.() -> Unit
    ): T {
        return client.post {
            setBody(requestBody)
            url.apply(urlBuilder)
            contentType(ContentType.Application.Json)
        }.body()
    }

}