package dev.shushant.network.api

import dev.shushant.network.BaseAPI
import dev.shushant.network.NetworkFailure
import dev.shushant.network.functional.Either
import dev.shushant.network.functional.flatMap
import dev.shushant.network.model.ConfirmEmailRequest
import dev.shushant.network.model.ConfirmEmailResponse
import dev.shushant.network.model.GetUsersDataRequest
import dev.shushant.network.model.LoginResponse
import dev.shushant.network.model.ResetPasswordCodeRequest
import dev.shushant.network.model.ResetPasswordSendCodeResponse
import dev.shushant.network.model.SendPasswordResetCode
import dev.shushant.network.model.SendPasswordResetCodeResponse
import dev.shushant.network.model.SignUpResponse
import dev.shushant.network.model.SignupRequest
import dev.shushant.network.model.UpdateEmailRequest
import dev.shushant.network.model.UpdateEmailResponse
import dev.shushant.network.model.UpdateUserInfo
import dev.shushant.network.model.UserData

class KnitTogetherApi : BaseAPI() {
    override val baseUrl: String
        get() = "https://identitytoolkit.googleapis.com/v1/accounts:"

    suspend fun getPosts(): Either<List<Any>, NetworkFailure> {
        return doGet<Any>("endPoint").flatMap { posts ->
            Either.Success(
                listOf(posts)
            )
        }
    }

    suspend fun signUp(request: SignupRequest): Either<SignUpResponse, NetworkFailure> {
        return doPost<SignUpResponse>(
            url = "{$baseUrl}signUp?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun signIn(request: SignupRequest): Either<LoginResponse, NetworkFailure> {
        return doPost<LoginResponse>(
            url = "{$baseUrl}signInWithPassword?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun sendCodeToResetPassword(request: ResetPasswordCodeRequest): Either<ResetPasswordSendCodeResponse, NetworkFailure> {
        return doPost<ResetPasswordSendCodeResponse>(
            url = "{$baseUrl}sendOobCode?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun confirmEmailVerification(request: ConfirmEmailRequest): Either<ConfirmEmailResponse, NetworkFailure> {
        return doPost<ConfirmEmailResponse>(
            url = "{$baseUrl}update?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun verifyResetCode(request: SendPasswordResetCode): Either<SendPasswordResetCodeResponse, NetworkFailure> {
        return doPost<SendPasswordResetCodeResponse>(
            url = "{$baseUrl}resetPassword?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun changeEmail(request: UpdateEmailRequest): Either<UpdateEmailResponse, NetworkFailure> {
        return doPost<UpdateEmailResponse>(
            url = "{$baseUrl}update?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun updateUserInfo(request: UpdateUserInfo): Either<UserData, NetworkFailure> {
        return doPost<UserData>(
            url = "{$baseUrl}update?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun getUserData(request: UpdateUserInfo): Either<GetUsersDataRequest, NetworkFailure> {
        return doPost<GetUsersDataRequest>(
            url = "{$baseUrl}lookup?key=",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
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
