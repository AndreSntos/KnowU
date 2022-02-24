package com.example.knowu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
    }

    fun verificaEmail(view: View) : Boolean {
        var email = findViewById<EditText>(R.id.ti_email);

        if(email.text.toString().isEmpty()) {
            email.error = "Digite um e-mail!"
            return false;
        } else if (!email.text.toString().contains('@')) {
            email.error = "Digite um e-mail válido!"
            return false;
        }
        return true;
    }
}