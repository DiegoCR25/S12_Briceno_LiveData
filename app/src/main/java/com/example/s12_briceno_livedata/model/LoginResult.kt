package com.example.s12_briceno_livedata.model

sealed class LoginResult {
    object Loading : LoginResult()
    object Success : LoginResult()
    data class Error(val message: String) : LoginResult()

}