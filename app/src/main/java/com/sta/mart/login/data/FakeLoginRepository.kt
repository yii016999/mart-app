package com.sta.mart.login.data

import com.sta.mart.login.domain.LoginRepository

class FakeLoginRepository(private val shouldSucceed: Boolean = true) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return if (shouldSucceed) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Login failed"))
        }
    }
}
