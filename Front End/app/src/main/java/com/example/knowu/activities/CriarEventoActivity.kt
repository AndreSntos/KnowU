package com.example.knowu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.knowu.R

class CriarEventoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_evento)
    }

    fun criarEvento(view: View) {
        if (validarCampos()){
            irTelaSucesso()
        }
    }

    fun validarCampos(): Boolean {
        var nome_evento = findViewById<EditText>(R.id.ti_nome_evento);
        var descricao = findViewById<EditText>(R.id.ti_descricao);
        var localidade = findViewById<EditText>(R.id.ti_localidade);
        var dialog = AlertDialog.Builder(this);
        dialog.setTitle("Atenção").setIcon(R.drawable.error)
            .setPositiveButton("Ok", null)

        if (nome_evento.text.toString().isEmpty() || descricao.text.toString()
                .isEmpty() || localidade.text.toString()
                .isEmpty()
        ) {
            dialog.setMessage("Todos os campos devem estar preenchidos!").show()
            return false;
        }
        return true;
    }

    fun irTelaSucesso(){
        startActivity(Intent(baseContext, SucessoActivity::class.java))
    }
}