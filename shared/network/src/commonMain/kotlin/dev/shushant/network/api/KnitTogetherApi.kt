package dev.shushant.network.api

import dev.shushant.network.BaseAPI
import dev.shushant.network.NetworkFailure
import dev.shushant.network.functional.Either
import dev.shushant.network.functional.flatMap

class KnitTogetherApi : BaseAPI() {
    override val baseUrl: String
        get() = "https://dummy.com/"

    suspend fun getPosts(): Either<List<Any>, NetworkFailure> {
        return doGet<Any>("endPoint").flatMap { breedResult ->
            //Converting BreedResult to List<TBreed>
            Either.Success(
                listOf()
            )
        }
    }

    suspend fun getImageBitmapByUrl(url: String): Either<ByteArray, NetworkFailure> {
        return doGetWithByteArray<ByteArray>(url = url, endPoint = "").flatMap { result ->
            Either.Success(
                result
            )
        }
    }
}