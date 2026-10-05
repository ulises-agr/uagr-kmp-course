package com.uagr.kmp.course.domain.usecase.home

import org.koin.core.annotation.Factory

@Factory
class ValidateHomeFormUseCase {
    operator fun invoke(): HomeValidationResult = HomeValidationResult.Success
}
