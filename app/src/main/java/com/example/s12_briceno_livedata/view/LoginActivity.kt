package com.example.s12_briceno_livedata.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.s12_briceno_livedata.R
import com.example.s12_briceno_livedata.model.LoginResult
import com.example.s12_briceno_livedata.viewmodel.LoginViewModel

class LoginActivity : ComponentActivity() {

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)

        loginViewModel.loginState.observe(this) { result ->
            when (result) {
                is LoginResult.Loading -> {
                    Toast.makeText(this, "Cargando...", Toast.LENGTH_SHORT).show()
                }

                is LoginResult.Success -> {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                    // Puedes navegar a otra actividad aquí
                    startActivity(Intent(this, BienvenidaActivity::class.java))
                    finish() // opcional, para no volver atrás con el botón
                }
                is LoginResult.Error -> {
                    Toast.makeText(this, "Error: ${result.message}", Toast.LENGTH_LONG).show()
                }
            }
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            loginViewModel.login(email, password)
        }

        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            loginViewModel.register(email, password)
        }

    }

}