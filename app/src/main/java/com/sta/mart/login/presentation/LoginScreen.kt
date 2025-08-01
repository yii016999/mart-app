package com.sta.mart.login.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sta.mart.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    // Collect the state from the ViewModel
    val state by viewModel.state.collectAsState()
    // State to hold the user's account & secret
    var account by remember { mutableStateOf("") }
    var secret by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(id = R.dimen.login_horizontal_padding)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        // Title text: "Login"
        Text(
            text = "Login",
            fontSize = dimensionResource(id = R.dimen.login_title_font_size).value.sp,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.login_text_input_spacer_height)))

        // TextInput title
        Text(
            text = "Enter your account and password",
            fontSize = dimensionResource(id = R.dimen.login_subtitle_font_size).value.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.login_title_vertical_spacing)))

        // Title Account
        Text(
            text = "Account",
            fontSize = dimensionResource(id = R.dimen.login_subtitle_font_size).value.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        // TextInput Account
        TextField(
            value = account,
            onValueChange = { account = it },
            label = { Text("Please input your account") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,    // When the text field is focused
                unfocusedContainerColor = Color.Transparent,  // When the text field is not focused
                disabledContainerColor = Color.Transparent,   // When the text field is disabled
                focusedIndicatorColor = Color.Black,          // When the text field is focused, bottom line color
                unfocusedIndicatorColor = Color.Gray,         // When the text field is not focused, bottom line color
                disabledIndicatorColor = Color.LightGray      // disabled bottom line color
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.login_text_input_vertical_spacing)))

        // Title Password
        Text(
            text = "Password",
            fontSize = dimensionResource(id = R.dimen.login_subtitle_font_size).value.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        // TextInput Password
        TextField(
            value = secret,
            onValueChange = { secret = it },
            label = { Text("Please input your password") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,    // When the text field is focused
                unfocusedContainerColor = Color.Transparent,  // When the text field is not focused
                disabledContainerColor = Color.Transparent,   // When the text field is disabled
                focusedIndicatorColor = Color.Black,          // When the text field is focused, bottom line color
                unfocusedIndicatorColor = Color.Gray,         // When the text field is not focused, bottom line color
                disabledIndicatorColor = Color.LightGray      // disabled bottom line color
            )
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.login_text_input_vertical_spacing)))

        // Login button
        Button(
            onClick = { viewModel.login(account, secret) },
            enabled = state !is LoginState.Loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state is LoginState.Loading) {
                // Loading state, show a circular progress indicator
                CircularProgressIndicator(modifier = Modifier.size(dimensionResource(id = R.dimen.loading_indicator_size)))
            } else {
                Text("登入")
            }
        }
    }
}
