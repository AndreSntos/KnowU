package com.example.knowu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.knowu.R

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)
    }

    fun validarSenha(etSenha: EditText, etConfirmaSenha: EditText) : Boolean {
        var dialog = AlertDialog.Builder(this);
        dialog.setTitle("Atenção").setIcon(R.drawable.error)
            .setPositiveButton("Ok", null)

        if(etSenha.text.toString().isEmpty()) {
            etSenha.error = "Digite uma senha!"
            dialog.setMessage("Digite uma senha!").show()
            return false;
        }

        if(etSenha.text.toString().isEmpty()) {
            etSenha.error = "Digite uma senha!"
            dialog.setMessage("Digite uma senha!").show()
            return false;
        }

        if(!etSenha.text.toString().equals(etConfirmaSenha.text.toString())) {
            etSenha.error = "Senhas não coincidem!"
            dialog.setMessage("Senhas não coincidem!").show()
            return false;
        }
        return true;
    }

    fun trocaSenha(v: View){
        var senha = findViewById<EditText>(R.id.et_senha);
        var confirmarSenha = findViewById<EditText>(R.id.et_confirmar);
        if (validarSenha(senha, confirmarSenha)){
            val proximaTela: Intent = Intent(baseContext, PasswordSucessActivity::class.java)
            startActivity(proximaTela)
        }
    }

}