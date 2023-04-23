package dev.shushant.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class LoginResponse(
    @SerialName("displayName")
    val displayName: String? = "",
    @SerialName("email")
    val email: String? = "", // shushanttiwari.ashu@outlook.com
    @SerialName("expiresIn")
    val expiresIn: String? = "", // 3600
    @SerialName("idToken")
    val idToken: String? = "", // eyJhbGciOiJSUzI1NiIsImtpZCI6IjE2ZGE4NmU4MWJkNTllMGE4Y2YzNTgwNTJiYjUzYjUzYjE4MzA3NzMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20va25pdHRvZ2V0aGVyLWYzOTdjIiwiYXVkIjoia25pdHRvZ2V0aGVyLWYzOTdjIiwiYXV0aF90aW1lIjoxNjgyMzAxMTQ1LCJ1c2VyX2lkIjoiM1dzRHA1UTY1ZmQwVkRuT3Ric1MyUFpYRTdQMiIsInN1YiI6IjNXc0RwNVE2NWZkMFZEbk90YnNTMlBaWEU3UDIiLCJpYXQiOjE2ODIzMDExNDUsImV4cCI6MTY4MjMwNDc0NSwiZW1haWwiOiJzaHVzaGFudHRpd2FyaS5hc2h1QG91dGxvb2suY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbInNodXNoYW50dGl3YXJpLmFzaHVAb3V0bG9vay5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.PdkVaf_2wXnpoknr3Pyi3Ket_6cGPWgg_VjxHpiu6ZjkHAgAYKzJVMY7VP8d_J_KTkTXUqWpjOJEooOXMNTE-J5xS0Y_lmRQz5u8GoFWH9ijQqhLRSaXfAWWathIa0WR4TYPKp0i-iaNQd1s66SQWbIzlR6ymsIvENBIic5kGG2E-weVPF_BPym7xHV7D90pYc-NFZOkvsRQuuvdl3MObNXg8cO4c4E9CKgZh_-yXh2oGGzYgN7qj2U_gLhjsAGomrzFDbenVaAfabHcxWS2F1AGA12Ll1KSi5MVm7zK5ceuSroL_lOMTVABbxUszDVrerLbXfvPonWnZdzoWQKTYQ
    @SerialName("kind")
    val kind: String? = "", // identitytoolkit#VerifyPasswordResponse
    @SerialName("localId")
    val localId: String? = "", // 3WsDp5Q65fd0VDnOtbsS2PZXE7P2
    @SerialName("refreshToken")
    val refreshToken: String? = "", // APJWN8e4lzNzI_0rFDjN0opFi9lpiwJa-LJ9qHHmoEsCc7i5PngEMngOylLUrYALCR6gWUUu4L9k0D-NJgjzP1q_heIg9hlfsujgYAUU-nQ_Jov471jujnP_TbaHbIJpmHJzFyG0ZqFdiQqPWdej44Vt2nHBETtq559rnmfaTxmfA8Br8J08Vnyxbh15N4YOpH8nijgxXi1g-6rwh5td5z91SQqHrSBrYbuV7_r8sZawRo308Pn74os
    @SerialName("registered")
    val registered: Boolean? = false // true
)