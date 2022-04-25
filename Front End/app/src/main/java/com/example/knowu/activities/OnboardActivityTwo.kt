package com.example.knowu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class OnboardActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard_two)
    }

    fun nextPag(v: View){
        val tela3: Intent = Intent(
            baseContext,
            OnboardActivityThree::class.java
        )
        startActivity(tela3)
    }
}