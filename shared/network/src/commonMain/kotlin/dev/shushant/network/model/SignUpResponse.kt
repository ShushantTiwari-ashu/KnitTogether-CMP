package dev.shushant.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignUpResponse(
    @SerialName("email")
    val email: String? = "", // shushanttiwari.ashu@outlook.com
    @SerialName("expiresIn")
    val expiresIn: String? = "", // 3600
    @SerialName("idToken")
    val idToken: String? = "", // eyJhbGciOiJSUzI1NiIsImtpZCI6IjE2ZGE4NmU4MWJkNTllMGE4Y2YzNTgwNTJiYjUzYjUzYjE4MzA3NzMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20va25pdHRvZ2V0aGVyLWYzOTdjIiwiYXVkIjoia25pdHRvZ2V0aGVyLWYzOTdjIiwiYXV0aF90aW1lIjoxNjgyMjY3ODk2LCJ1c2VyX2lkIjoiM1dzRHA1UTY1ZmQwVkRuT3Ric1MyUFpYRTdQMiIsInN1YiI6IjNXc0RwNVE2NWZkMFZEbk90YnNTMlBaWEU3UDIiLCJpYXQiOjE2ODIyNjc4OTYsImV4cCI6MTY4MjI3MTQ5NiwiZW1haWwiOiJzaHVzaGFudHRpd2FyaS5hc2h1QG91dGxvb2suY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbInNodXNoYW50dGl3YXJpLmFzaHVAb3V0bG9vay5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.SYB_3bFldH1z2rdjVwN2h6aXQeqN-uDFd2tAZeZeexo013nljTlY3Sq5QxwSlCvppD6A9QA_MoXANaZMm0MukiN4JubKbi4HkQLV4A9qqG4bgjEKFb1l8LgnZ3nuj0B4a46xuh4k0cJCjVrdGYhJlkdVKlXez872XyYifW0oDRulXfvORMKVUFymShdYUhzz2s5IMPraEQqFIY9j4PVMmdPalZWxHlqLahmBTkmofUMJu7BUVIWNBxJ6wMSKw7HzI1KIf_RJoM8q_jHqOqjqjRcjO2d72Q7L8wAsNa8W3b2vYLhGbdmsqjEOeevdvzSATJh1bOQYa9vCouaahP2EvQ
    @SerialName("kind")
    val kind: String? = "", // identitytoolkit#SignupNewUserResponse
    @SerialName("localId")
    val localId: String? = "", // 3WsDp5Q65fd0VDnOtbsS2PZXE7P2
    @SerialName("refreshToken")
    val refreshToken: String? = "" // APJWN8chDcF4PuTpiL5-NhK-cXKO9PGKngmcD3iyjQfa07yypq4zEyyT2F3b1bTTTQhVWYN1gvAuU2rHBynyWg9hvzmRJS4ZBQJc4AHj57fDB8_eVWrSsNYwn1GsmKdrw2i5Dz8i5d4CG7xe7Tho5l8z12U2K4cl46sm4flDBZQ6-E8ZU73eDrQSLCMDIfyK970WOeLi0p4ihtiLlARR3-CHgKJOml8fffQbZcoRtyi2aVCpC2ymCGE
)