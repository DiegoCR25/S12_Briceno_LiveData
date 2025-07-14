package com.example.s12_briceno_livedata.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.s12_briceno_livedata.R
import com.google.firebase.auth.FirebaseAuth

class BienvenidaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val tvTitulo = findViewById<TextView>(R.id.tvWelcomeTitle)
        val tvCorreo = findViewById<TextView>(R.id.tvWelcomeSubtitle)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val user = FirebaseAuth.getInstance().currentUser
        tvCorreo.text = user?.email ?: "Usuario desconocido"

        btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}