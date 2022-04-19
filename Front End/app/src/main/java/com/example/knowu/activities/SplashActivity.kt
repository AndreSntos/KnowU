package com.example.knowu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.knowu.R
import com.example.knowu.model.Usuario
import com.example.knowu.rest.Rest
import com.example.knowu.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()

        Handler().postDelayed({
          val intent = Intent(this@SplashActivity, InicioAppActivity::class.java)
          startActivity(intent)
            finish()
        }, 3000)
    }
}