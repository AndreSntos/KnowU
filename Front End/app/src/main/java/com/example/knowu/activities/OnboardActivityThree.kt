package com.example.knowu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OnboardActivityThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard_three)
    }

    fun sighIn(v: View){
        val telaCadastro: Intent = Intent(
            baseContext,
            OnboardActivityThree::class.java
        )
        startActivity(telaCadastro)
    }

    fun sighUp(v: View){
        val telaLogin: Intent = Intent(
            baseContext,
            OnboardActivityThree::class.java
        )
        startActivity(telaLogin)
    }
}