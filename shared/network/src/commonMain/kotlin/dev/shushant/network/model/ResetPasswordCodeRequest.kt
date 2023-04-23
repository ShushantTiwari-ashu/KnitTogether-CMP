package dev.shushant.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ResetPasswordCodeRequest(
    @SerialName("email")
    val email: String? = "",
    @SerialName("requestType")
    val requestType: String? = "" // PASSWORD_RESET
)


data class SendPasswordResetCode(
    @SerialName("oobCode")
    val oobCode: String? = "", // [PASSWORD_RESET_CODE]
    @SerialName("newPassword")
    val newPassword: String? = "" // [PASSWORD_RESET_CODE]
)

data class SendPasswordResetCodeResponse(
    @SerialName("email")
    val email: String? = "", // [user@example.com]
    @SerialName("requestType")
    val requestType: String? = "" // PASSWORD_RESET
)