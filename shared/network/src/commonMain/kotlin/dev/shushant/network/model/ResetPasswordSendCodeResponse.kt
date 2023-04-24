package dev.shushant.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ResetPasswordSendCodeResponse(
    @SerialName("email")
    val email: String? = "" // [user@example.com]
)
data class GetUsersDataRequest(
    @SerialName("idToken")
    val idToken: String? = "" // [user@example.com]
)

data class ConfirmEmailRequest(
    @SerialName("oobCode")
    val oobCode: String? = "" // [user@example.com]
)

data class ConfirmEmailResponse(
    @SerialName("email")
    val email: String? = "", // user@example.com
    @SerialName("localId")
    val localId: String? = "", // FhyStE...
    @SerialName("passwordHash")
    val passwordHash: String? = "", // ...
    @SerialName("providerUserInfo")
    val providerUserInfo: List<ProviderUserInfo?>? = listOf()
) {

    data class ProviderUserInfo(
        @SerialName("federatedId")
        val federatedId: String? = "", // user@example.com
        @SerialName("providerId")
        val providerId: String? = "" // password
    )
}