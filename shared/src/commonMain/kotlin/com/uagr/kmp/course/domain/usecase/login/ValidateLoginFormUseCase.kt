/*
 * ValidateLoginFormUseCase.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.domain.usecase.login

import com.uagr.kmp.course.utils.text.validateEmailFormat

class ValidateLoginFormUseCase {

    operator fun invoke(
        email : String,
        password : String
    ) : ValidateLoginFormResult{
        
        if(email.isBlank() && password.isBlank()){
            return ValidateLoginFormResult.EmptyFields
        }
        
        if(email.isBlank()) {
          return ValidateLoginFormResult.EmailEmpty
        }
        
        if(!validateEmailFormat(email)){
            return ValidateLoginFormResult.WrongEmailFormat
        }
        
        
        if(password.isBlank()){
            return ValidateLoginFormResult.PasswordEmpty
        }
        
        return ValidateLoginFormResult.Success
    }
}
