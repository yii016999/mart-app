package com.sta.mart.login.presentation

import app.cash.turbine.test
import com.sta.mart.TestConstants.TEST_EMAIL
import com.sta.mart.TestConstants.TEST_PASSWORD
import com.sta.mart.TestConstants.TEST_WRONG_EMAIL
import com.sta.mart.TestConstants.TEST_WRONG_PASSWORD
import com.sta.mart.login.data.FakeLoginRepository
import com.sta.mart.login.domain.LoginUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel

    @Test
    fun `initial state should be Idle`() = runTest {
        // Given
        // create an instance of LoginViewModel
        val fakeRepo = FakeLoginRepository()
        val useCase = LoginUseCase(fakeRepo)
        viewModel = LoginViewModel(useCase)

        // When & Then
        // assert that the initial state is Idle
        assertEquals(LoginState.Idle, viewModel.state.value)
    }

    @Test
    fun `press login should show loading`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository
        val fakeRepo = FakeLoginRepository(shouldSucceed = true)
        val useCase = LoginUseCase(fakeRepo)
        viewModel = LoginViewModel(useCase)

        // When & Then
        // collect the state flow and assert the states
        viewModel.state.test {
            viewModel.login(TEST_EMAIL, TEST_PASSWORD)

            // Initial state => Idle
            awaitItem()

            // First state => Loading
            assertEquals(LoginState.Loading, awaitItem())

            // cancel the flow to stop collecting, this can avoid memory leaks
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `login with right credentials should emit success state`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository
        val fakeRepo = FakeLoginRepository(shouldSucceed = true)
        val useCase = LoginUseCase(fakeRepo)
        viewModel = LoginViewModel(useCase)

        // When + Then
        viewModel.state.test {
            viewModel.login(TEST_EMAIL, TEST_PASSWORD)

            // Initial state => Idle
            awaitItem()

            // First state => Loading
            assertEquals(LoginState.Loading, awaitItem())
            // Second state => Success
            assertEquals(LoginState.Success, awaitItem())

            // cancel the flow to stop collecting, this can avoid memory leaks
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `login with wrong credentials should emit Error state`() = runTest {
        // Given
        // create an instance of LoginViewModel with a fake AuthRepository that simulates a failure
        val fakeRepo = FakeLoginRepository(shouldSucceed = false)
        val useCase = LoginUseCase(fakeRepo)
        viewModel = LoginViewModel(useCase)

        // When + Then
        viewModel.state.test {
            viewModel.login(TEST_WRONG_EMAIL, TEST_WRONG_PASSWORD)

            // Initial state => Idle
            awaitItem()

            // First state => Loading
            assertEquals(LoginState.Loading, awaitItem())
            // Second state => Error
            assertEquals(LoginState.Error("Login failed"), awaitItem())

            cancelAndConsumeRemainingEvents()
        }
    }
}
