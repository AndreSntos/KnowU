package com.example.knowu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.knowu.activities.SignInActivity
import com.example.knowu.activities.SignUpActivity

class OnboardActivityThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard_three)
    }

    fun signUp(v: View){
        val telaCadastro: Intent = Intent(
            baseContext,
            SignUpActivity::class.java
        )
        startActivity(telaCadastro)
    }

    fun signIn(v: View){
        val telaLogin: Intent = Intent(
            baseContext,
            SignInActivity::class.java
        )
        startActivity(telaLogin)
    }
}