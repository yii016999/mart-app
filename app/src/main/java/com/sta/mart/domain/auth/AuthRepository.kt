package com.sta.mart.domain.auth

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
}
