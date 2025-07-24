package com.sta.mart.domain.login

interface LoginUseCase {
    suspend operator fun invoke(username: String, password: String): String
}