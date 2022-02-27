package com.example.knowu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.toColor

class EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
    }


    fun verificaEmail(view: View) : Boolean {
        var email = findViewById<EditText>(R.id.ti_email);
        var dialog = AlertDialog.Builder(this);
        dialog.setTitle("Atenção").setIcon(R.drawable.error)
            .setPositiveButton("Ok", null)

        if(email.text.toString().isEmpty()) {
            email.error = "Digite um e-mail!"
            dialog.setMessage("Digite um e-mail!").show()
            return false;
        } else if (!email.text.toString().contains('@')) {
            email.error = "Digite um e-mail válido!"
            dialog.setMessage("Digite um e-mail válido!").show()
            return false;
        }
        return true;
    }

}