package com.sta.mart.presentation.login

import app.cash.turbine.test
import com.sta.mart.domain.auth.FakeAuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    companion object {
        private const val TEST_EMAIL = "user@example.com"
        private const val TEST_PASSWORD = "example123"

        private const val TEST_WRONG_EMAIL = "wrong@"
        private const val TEST_WRONG_PASSWORD = "wrong123"
    }

    private lateinit var viewModel: LoginViewModel

    @Test
    fun `initial state should be Idle`() = runTest {
        // Given
        // create an instance of LoginViewModel
        val fakeRepo = FakeAuthRepository()
        viewModel = LoginViewModel(authRepository = fakeRepo)

        // When & Then
        // assert that the initial state is Idle
        assertEquals(LoginViewModel.LoginState.Idle, viewModel.state.value)
    }

    @Test
    fun `press login should show loading`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository
        val fakeRepo = FakeAuthRepository(shouldSucceed = true)
        viewModel = LoginViewModel(authRepository = fakeRepo)

        // When & Then
        // collect the state flow and assert the states
        viewModel.state.test {
            viewModel.login(TEST_EMAIL, TEST_PASSWORD)
            // First state => Loading
            assertEquals(LoginViewModel.LoginState.Loading, awaitItem())

            // cancel the flow to stop collecting, this can avoid memory leaks
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `login with right credentials should emit success state`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository
        val fakeRepo = FakeAuthRepository(shouldSucceed = true)
        viewModel = LoginViewModel(fakeRepo)

        // When + Then
        viewModel.state.test {
            viewModel.login(TEST_EMAIL, TEST_PASSWORD)

            // First state => Loading
            assertEquals(LoginViewModel.LoginState.Loading, awaitItem())
            // Second state => Success
            assertEquals(LoginViewModel.LoginState.Success, awaitItem())

            // cancel the flow to stop collecting, this can avoid memory leaks
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `login with wrong credentials should emit Error state`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository that simulates a failure
        val fakeRepo = FakeAuthRepository(shouldSucceed = false)
        viewModel = LoginViewModel(fakeRepo)

        // When + Then
        viewModel.state.test {
            viewModel.login(TEST_WRONG_EMAIL, TEST_WRONG_PASSWORD)

            // First state => Loading
            assertEquals(LoginViewModel.LoginState.Loading, awaitItem())
            // Second state => Error
            assertEquals(LoginViewModel.LoginState.Error("Login failed"), awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}