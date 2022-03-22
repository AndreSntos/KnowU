package com.example.knowu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class SingInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun validFieldsLogin(
        etLogin: EditText,
        etPassword: EditText): Boolean{
        var dialog = AlertDialog.Builder(this);
        dialog.setTitle("Erro").setIcon(R.drawable.error)
            .setPositiveButton("Ok", null)

        if (etLogin.text.toString().isEmpty()){
            etLogin.error = "Digite seu nome de usuário!"
            return false
        }
        else if (etLogin.text.toString().isBlank()){
            etLogin.error = "Digite seu nome de usuário!"
            return false
        }
        else if(etPassword.text.toString().isEmpty()){
            etPassword.error = "Digite sua senha!"
            return false
        }
        else if (etLogin.text.toString().isEmpty() && etPassword.text.toString().isEmpty()){
            dialog.setMessage("Preencha todos os campos!").show()
            return false;
        }

        return true
    }

    fun loginSucess(v: View){
        var loginUser = findViewById<EditText>(R.id.ti_login)
        var passwordUser = findViewById<EditText>(R.id.ti_senha)

        if (validFieldsLogin(loginUser, passwordUser)){
            // Vai pra tela home de eventos
        } else {

        }
    }
}