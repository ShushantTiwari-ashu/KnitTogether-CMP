package dev.shushant.permission

class RequestCanceledException(
    val permission: Permission,
    message: String? = null
) : Exception(message)
