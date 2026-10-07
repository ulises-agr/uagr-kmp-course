sealed class RegisterResult {

    data object Success : RegisterResult()

    data class ValidationError(
        val nameError: String? = null,
        val emailError: String? = null,
        val passwordError: String? = null,
        val confirmPasswordError: String? = null
    ) : RegisterResult()

    data object RegisterFailed : RegisterResult()
}