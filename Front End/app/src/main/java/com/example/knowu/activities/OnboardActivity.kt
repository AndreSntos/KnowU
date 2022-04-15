package com.example.knowu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OnboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
    }

    fun nextPag(v: View){
        val tela2: Intent = Intent(
            baseContext,
            OnboardActivityTwo::class.java
        )
        startActivity(tela2)
    }

}