package com.sta.mart.domain.auth

import com.sta.mart.data.fake.AuthRepository

class FakeAuthRepository(private val shouldSucceed: Boolean = true) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (shouldSucceed) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Login failed"))
        }
    }
}