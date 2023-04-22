package dev.shushant.network

sealed class Failure(val message: String)

class NetworkFailure(exception: Exception) : Failure(exception.message.toString())