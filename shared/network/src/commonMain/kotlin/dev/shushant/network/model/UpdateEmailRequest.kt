package dev.shushant.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


data class UpdateEmailRequest(
    @SerialName("email")
    val email: String? = "", // [user@example2.com]
    @SerialName("idToken")
    val idToken: String? = "", // [FIREBASE_ID_TOKEN]
    @SerialName("returnSecureToken")
    val returnSecureToken: Boolean? = false // true
)
data class UpdateUserInfo(
    @SerialName("displayName")
    val displayName: String? = "", // [NAME]
    @SerialName("idToken")
    val idToken: String? = "", // [ID_TOKEN]
    @SerialName("photoUrl")
    val photoUrl: String? = "", // [URL]
    @SerialName("returnSecureToken")
    val returnSecureToken: Boolean? = false // true
)

data class UpdateEmailResponse(
    @SerialName("email")
    val email: String? = "", // [user@example2.com]
    @SerialName("expiresIn")
    val expiresIn: String? = "", // 3600
    @SerialName("idToken")
    val idToken: String? = "", // [NEW_ID_TOKEN]
    @SerialName("localId")
    val localId: String? = "", // tRcfmLH7o2...
    @SerialName("passwordHash")
    val passwordHash: String? = "", // ...
    @SerialName("providerUserInfo")
    val providerUserInfo: List<ProviderUserInfo?>? = listOf(),
    @SerialName("refreshToken")
    val refreshToken: String? = "" // [NEW_REFRESH_TOKEN]
) {
    data class ProviderUserInfo(
        @SerialName("federatedId")
        val federatedId: String? = "", // [user@example2.com]
        @SerialName("providerId")
        val providerId: String? = "" // password
    )
}

data class UserData(
    @SerialName("displayName")
    val displayName: String? = "", // John Doe
    @SerialName("email")
    val email: String? = "", // user@example2.com
    @SerialName("expiresIn")
    val expiresIn: String? = "", // 3600
    @SerialName("idToken")
    val idToken: String? = "", // [NEW_ID_TOKEN]
    @SerialName("localId")
    val localId: String? = "", // tRcfmLH...
    @SerialName("passwordHash")
    val passwordHash: String? = "", // ...
    @SerialName("photoUrl")
    val photoUrl: String? = "", // [http://localhost:8080/img1234567890/photo.png]
    @SerialName("providerUserInfo")
    val providerUserInfo: List<ProviderUserInfo?>? = listOf(),
    @SerialName("refreshToken")
    val refreshToken: String? = "" // [NEW_REFRESH_TOKEN]
) {
    data class ProviderUserInfo(
        @SerialName("displayName")
        val displayName: String? = "", // John Doe
        @SerialName("federatedId")
        val federatedId: String? = "", // user@example2.com
        @SerialName("photoUrl")
        val photoUrl: String? = "", // http://localhost:8080/img1234567890/photo.png
        @SerialName("providerId")
        val providerId: String? = "" // password
    )
}


data class GetUserDataResponse(
    @SerialName("users")
    val users: List<User?>? = listOf()
) {
    data class User(
        @SerialName("createdAt")
        val createdAt: String? = "", // 1484124142000
        @SerialName("customAuth")
        val customAuth: Boolean? = false, // false
        @SerialName("disabled")
        val disabled: Boolean? = false, // false
        @SerialName("displayName")
        val displayName: String? = "", // John Doe
        @SerialName("email")
        val email: String? = "", // user@example.com
        @SerialName("emailVerified")
        val emailVerified: Boolean? = false, // false
        @SerialName("lastLoginAt")
        val lastLoginAt: String? = "", // 1484628946000
        @SerialName("localId")
        val localId: String? = "", // ZY1rJK0...
        @SerialName("passwordHash")
        val passwordHash: String? = "", // ...
        @SerialName("passwordUpdatedAt")
        val passwordUpdatedAt: Double? = 0.0, // 1.484124177E12
        @SerialName("photoUrl")
        val photoUrl: String? = "", // https://lh5.googleusercontent.com/.../photo.jpg
        @SerialName("providerUserInfo")
        val providerUserInfo: List<ProviderUserInfo?>? = listOf(),
        @SerialName("validSince")
        val validSince: String? = "" // 1484124177
    ) {

        data class ProviderUserInfo(
            @SerialName("displayName")
            val displayName: String? = "", // John Doe
            @SerialName("email")
            val email: String? = "", // user@example.com
            @SerialName("federatedId")
            val federatedId: String? = "", // user@example.com
            @SerialName("photoUrl")
            val photoUrl: String? = "", // http://localhost:8080/img1234567890/photo.png
            @SerialName("providerId")
            val providerId: String? = "", // password
            @SerialName("rawId")
            val rawId: String? = "", // user@example.com
            @SerialName("screenName")
            val screenName: String? = "" // user@example.com
        )
    }
}