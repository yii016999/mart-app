package com.sta.mart.data.fake

import com.sta.mart.domain.auth.AuthRepository

class FakeAuthRepository(private val shouldSucceed: Boolean = true) : AuthRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (shouldSucceed) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Login failed"))
        }
    }
}
