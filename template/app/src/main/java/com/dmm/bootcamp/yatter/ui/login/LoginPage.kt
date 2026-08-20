package com.dmm.bootcamp.yatter.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginPage(
  onLoggedIn: () -> Unit,
  onNavigateToRegister: () -> Unit,
  loginViewModel: LoginViewModel = koinViewModel(),
) {
  val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()

  LaunchedEffect(loginViewModel) {
    loginViewModel.navigationEvent.collect { event ->
      when (event) {
        LoginNavigationEvent.LoggedIn -> {
          onLoggedIn()
        }

        LoginNavigationEvent.NavigatedToRegister -> {
          onNavigateToRegister()
        }
      }
    }
  }

  LoginTemplate(
    userName = uiState.loginBindingModel.username,
    onChangedUserName = loginViewModel::onChangedUsername,
    password = uiState.loginBindingModel.password,
    onChangedPassword = loginViewModel::onChangedPassword,
    isEnableLogin = uiState.isEnableLogin,
    isLoading = uiState.isLoading,
    onClickLogin = loginViewModel::onClickLogin,
    onClickRegister = loginViewModel::onClickRegister,
  )
}
