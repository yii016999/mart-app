package com.sta.mart.login.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.sta.mart.login.domain.LoginRepository
import com.sta.mart.login.domain.LoginUseCase
import com.sta.mart.ui.theme.MartTheme

// Only for preview
class PreviewLoginRepository : LoginRepository {
    override suspend fun login(email: String, password: String): Result<Unit> {
        return Result.failure(Exception("Preview Error"))
    }
}

// Preview useCase
val previewLoginUseCase = LoginUseCase(PreviewLoginRepository())

// Preview ViewModel
class PreviewLoginViewModel : LoginViewModel(previewLoginUseCase) {
    init {
        setPreviewState(LoginState.Idle)
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val fakeViewModel = remember { PreviewLoginViewModel() }
    MartTheme {
        LoginScreen(viewModel = fakeViewModel)
    }
}

@Preview(showBackground = true, name = "Error")
@Composable
fun LoginScreenPreviewError() {
    val fakeViewModel = remember { PreviewLoginViewModel().apply { setPreviewState(LoginState.Error("預覽錯誤")) } }
    MartTheme {
        LoginScreen(viewModel = fakeViewModel)
    }
}