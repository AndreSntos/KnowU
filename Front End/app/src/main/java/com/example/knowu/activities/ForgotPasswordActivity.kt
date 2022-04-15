package com.example.knowu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.knowu.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
    }

    fun verificarCodigoEnviado(v: View){
        var codigo = findViewById<EditText>(R.id.et_codigo);
        if (validaCodigo(codigo)){
            val proximaTela: Intent = Intent(baseContext, ChangePassword::class.java)
            startActivity(proximaTela)
        }
    }

    fun validaCodigo(etCodigo: EditText) : Boolean {
        var dialog = AlertDialog.Builder(this)
        dialog.setTitle("Atenção").setIcon(R.drawable.error)
            .setPositiveButton("Ok", null)

        if(etCodigo.text.toString().isEmpty()) {
            etCodigo.error = "Digite o codigo!"
            dialog.setMessage("Digite o codigo!").show()
            return false;
        }
        return true;
    }
}