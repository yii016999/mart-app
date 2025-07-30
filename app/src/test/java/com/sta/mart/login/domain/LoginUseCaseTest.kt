package com.sta.mart.login.domain

import com.sta.mart.TestConstants.TEST_EMAIL
import com.sta.mart.TestConstants.TEST_PASSWORD
import com.sta.mart.TestConstants.TEST_WRONG_EMAIL
import com.sta.mart.TestConstants.TEST_WRONG_PASSWORD
import com.sta.mart.login.data.FakeLoginRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginUseCaseTest {

    private lateinit var loginUseCase: LoginUseCase

    @Test
    fun `successful login should return Result success`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository
        val fakeRepo = FakeLoginRepository(shouldSucceed = true)
        loginUseCase = LoginUseCase(fakeRepo)

        // When
        // Invoking LoginUseCase with valid email and password
        val result = loginUseCase(TEST_EMAIL, TEST_PASSWORD)

        // Then
        // The result should be a successful Result
        assertTrue(result.isSuccess)
    }

    @Test
    fun `failed login should return Result failure`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository
        val fakeRepo = FakeLoginRepository(shouldSucceed = false)
        loginUseCase = LoginUseCase(fakeRepo)

        // When
        // Invoking LoginUseCase with valid email and password
        val result = loginUseCase(TEST_WRONG_EMAIL, TEST_WRONG_PASSWORD)

        // Then
        // The result should be a failure Result
        assertTrue(result.isFailure)
        assertEquals("Login failed", result.exceptionOrNull()?.message)
    }
}
