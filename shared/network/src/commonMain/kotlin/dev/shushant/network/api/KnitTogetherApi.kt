package dev.shushant.network.api

import dev.shushant.network.BaseAPI
import dev.shushant.network.HTTPHelper
import dev.shushant.network.NetworkFailure
import dev.shushant.network.extensions.FIREBASE_WEB_API_KEY
import dev.shushant.network.functional.Either
import dev.shushant.network.functional.flatMap
import dev.shushant.network.model.ConfirmEmailRequest
import dev.shushant.network.model.ConfirmEmailResponse
import dev.shushant.network.model.GetUserDataResponse
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
import kotlin.properties.PropertyDelegateProvider

class KnitTogetherApi(httpHelper: HTTPHelper) : BaseAPI(httpHelper) {
    private val baseUrl: String
        get() = "https://identitytoolkit.googleapis.com/v1/accounts:"

    suspend fun getPosts(): Either<List<Any>, NetworkFailure> {
        return doGet<Any>("endPoint").flatMap { posts ->
            Either.Success(
                listOf(posts)
            )
        }
    }

    suspend fun signUp(request: SignupRequest): Either<SignUpResponse, NetworkFailure> {
        return doPost<SignUpResponse, SignupRequest>(
            url = "${baseUrl}signUp?key=$FIREBASE_WEB_API_KEY",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun signIn(request: SignupRequest): Either<LoginResponse, NetworkFailure> {
        return doPost<LoginResponse, SignupRequest>(
            url = "${baseUrl}signInWithPassword?key=$FIREBASE_WEB_API_KEY",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun sendCodeToResetPassword(request: ResetPasswordCodeRequest): Either<ResetPasswordSendCodeResponse, NetworkFailure> {
        return doPost<ResetPasswordSendCodeResponse, ResetPasswordCodeRequest>(
            url = "${baseUrl}sendOobCode?key=$FIREBASE_WEB_API_KEY",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun confirmEmailVerification(request: ConfirmEmailRequest): Either<ConfirmEmailResponse, NetworkFailure> {
        return doPost<ConfirmEmailResponse, ConfirmEmailRequest>(
            url = "${baseUrl}update?key=$FIREBASE_WEB_API_KEY",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun verifyResetCode(request: SendPasswordResetCode): Either<SendPasswordResetCodeResponse, NetworkFailure> {
        return doPost<SendPasswordResetCodeResponse, SendPasswordResetCode>(
            url = "${baseUrl}resetPassword?key=$FIREBASE_WEB_API_KEY",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun changeEmail(request: UpdateEmailRequest): Either<UpdateEmailResponse, NetworkFailure> {
        return doPost<UpdateEmailResponse, UpdateEmailRequest>(
            url = "${baseUrl}update?key=$FIREBASE_WEB_API_KEY",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun updateUserInfo(request: UpdateUserInfo): Either<UserData, NetworkFailure> {
        return doPost<UserData, UpdateUserInfo>(
            url = "${baseUrl}update?key=$FIREBASE_WEB_API_KEY",
            endPoint = "",
            requestBody = request
        ).flatMap { user ->
            Either.Success(user)
        }
    }

    suspend fun getUserData(request: UpdateUserInfo): Either<GetUserDataResponse, NetworkFailure> {
        return doPost<GetUserDataResponse, UpdateUserInfo>(
            url = "${baseUrl}lookup?key=$FIREBASE_WEB_API_KEY",
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
