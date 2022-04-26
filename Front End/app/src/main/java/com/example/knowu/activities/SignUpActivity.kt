package com.example.knowu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.knowu.R
import com.example.knowu.model.Usuario

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
            return false;
        } else if (!etPasswordConfirm.text.toString()
                .equals(etPassword.text.toString()) || etPasswordConfirm.text.toString().isEmpty()
        ) {
            etPasswordConfirm.error = "Senhas não coincidem"
            return false;
        }

        return true
    }

    fun nextStep(v: View) {
        var user = findViewById<EditText>(R.id.ti_login)
        var email = findViewById<EditText>(R.id.ti_email)
        var password = findViewById<EditText>(R.id.ti_senha)
        var passwordConfirm = findViewById<EditText>(R.id.ti_confirme_senha)

        if (validFields(user, email, password, passwordConfirm)) {
            val intent = Intent(this, SignUpTwoActivity::class.java)
            intent.putExtra("user", user.text.toString())
            intent.putExtra("email", email.text.toString())
            intent.putExtra("password", password.text.toString())
            startActivity(intent)
        }

    }
}