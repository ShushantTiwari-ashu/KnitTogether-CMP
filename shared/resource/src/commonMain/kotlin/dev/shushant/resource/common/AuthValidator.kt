package dev.shushant.resource.common

object AuthValidator {
    fun isValidEmail(email: String): Boolean =
        email.isNotBlank() && email.isNotEmpty() && isEmailValid(email)

    fun isValidPassword(password: String): Boolean = password.trim().length in (8..50)

    fun isPasswordAndConfirmPasswordSame(
        password: String,
        confirmedPassword: String
    ): Boolean = password.trim() == confirmedPassword.trim()

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")
        return emailRegex.matches(email)
    }
}