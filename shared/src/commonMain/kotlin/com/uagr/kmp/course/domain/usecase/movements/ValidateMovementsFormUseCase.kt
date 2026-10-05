package com.uagr.kmp.course.domain.usecase.movements

import org.koin.core.annotation.Factory

@Factory
class ValidateMovementsFormUseCase {
    operator fun invoke(): MovementsValidationResult = MovementsValidationResult.Success
}
