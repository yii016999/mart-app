package com.sta.mart.login.domain

class LoginUseCase(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return loginRepository.login(email, password)
    }
}
