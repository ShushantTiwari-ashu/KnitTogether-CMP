package dev.shushant.network.repo

import dev.shushant.network.Failure
import dev.shushant.network.functional.Either

interface BaseRepository<in InputParamsType, ReturnType> {
    suspend fun execute(inputParamsType: InputParamsType): Either<ReturnType, Failure>
}