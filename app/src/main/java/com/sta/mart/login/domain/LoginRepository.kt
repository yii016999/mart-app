package com.sta.mart.login.domain

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<Unit>
}
