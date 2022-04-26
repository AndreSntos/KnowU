package com.example.knowu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R

class PasswordSucessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_senha_alterada_sucesso)
    }

    fun voltarLogin(v: View){
        val proximaTela = Intent(baseContext, SingInActivity::class.java)
        startActivity(proximaTela)
    }
}