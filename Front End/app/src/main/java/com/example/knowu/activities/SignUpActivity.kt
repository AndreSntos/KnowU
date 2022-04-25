package com.example.knowu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.knowu.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun validFields(
        etUser: EditText,
        etEmail: EditText,
        etPassword: EditText,
        etPasswordConfirm: EditText
    ): Boolean {

        if (etUser.text.toString().isEmpty()) {
            etUser.error = "Digite um usuário para se cadastrar!"
            return false
        } else if (!etEmail.text.toString().contains('@') || etEmail.text.toString().isEmpty()) {
            etEmail.error = "Digite um e-mail válido!"
            return false;
        } else if (etPassword.text.toString().length <= 4 || etPassword.text.toString().isEmpty()) {
            etPassword.error = "Senha muito curta!"
        } else if (!etPasswordConfirm.text.toString()
                .equals(etPassword.text.toString()) || etPasswordConfirm.text.toString().isEmpty()
        ) {
            etPasswordConfirm.error = "Senhas não coincidem"
        }

        return true
    }

    fun nextStep(v: View) {
        var user = findViewById<EditText>(R.id.ti_login)
        var email = findViewById<EditText>(R.id.ti_email)
        var password = findViewById<EditText>(R.id.ti_login)
        var passwordConfirm = findViewById<EditText>(R.id.ti_confirme_senha)

        if (validFields(user, email, password, passwordConfirm)) {

        }

    }
}