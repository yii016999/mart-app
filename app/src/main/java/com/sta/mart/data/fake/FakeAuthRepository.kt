package com.sta.mart.data.fake

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Unit>
}