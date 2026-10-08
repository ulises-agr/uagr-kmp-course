import com.uagr.kmp.course.domain.usecase.register.RegisterValidationError

sealed class RegisterResult {

    data object Success : RegisterResult()

    data class ValidationError(
        val nameError: RegisterValidationError? = null,
        val emailError: RegisterValidationError? = null,
        val passwordError: RegisterValidationError? = null,
        val confirmPasswordError: RegisterValidationError? = null
    ) : RegisterResult()

    data object RegisterFailed : RegisterResult()
}