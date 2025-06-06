package com.mgoudarzi.movieapp.ui.login

interface LoginEvent {
  object OnLoginClick : LoginEvent
  object OnSignupClick : LoginEvent
  data class OnUsernameChange(val value: String) : LoginEvent
  data class OnPasswordChange(val value: String) : LoginEvent
}