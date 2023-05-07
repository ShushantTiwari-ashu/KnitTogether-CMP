package dev.shushant.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    @SerialName("email")
    val email: String? = "", // shushanttiwari.ashu@outlook.com
    @SerialName("password")
    val password: String? = "", // Ashu@1234
    @SerialName("returnSecureToken")
    val returnSecureToken: Boolean? = false // true
)