package com.example.s12_briceno_livedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s12_briceno_livedata.model.LoginResult
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel(){
    private val _loginState = MutableLiveData<LoginResult>()
    val loginState: LiveData<LoginResult> = _loginState

    fun login(email: String, password: String) {
        _loginState.value = LoginResult.Loading

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginState.value = LoginResult.Success
                } else {
                    _loginState.value = LoginResult.Error(task.exception?.message ?: "Error desconocido")
                }
            }
    }

    fun register(email: String, password: String) {
        _loginState.value = LoginResult.Loading

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _loginState.value = LoginResult.Success
                } else {
                    _loginState.value = LoginResult.Error(task.exception?.message ?: "Error al registrar")
                }
            }
    }

}