package com.example.knowu.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import com.example.knowu.R

class SignUpTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_two)
    }

    fun validFields(
        etName: EditText,
        etCPF: EditText,
        etNascimento: EditText,
        rdMasculino: RadioButton,
        rdFeminino: RadioButton
    ): Boolean {

        if (etName.text.toString().isEmpty()) {
            etName.error = "Digite um nome para se cadastrar!"
            return false
        } else if (etCPF.text.toString().length < 11 || etCPF.text.toString().isEmpty()) {
            etCPF.error = "Digite um cpf válido!"
            return false;
        }

        return true
    }

    fun nextStep(v: View) {
        var name = findViewById<EditText>(R.id.ti_nome_completo)
        var cpf = findViewById<EditText>(R.id.ti_cpf)
        var nascimento = findViewById<EditText>(R.id.ti_nascimento)
        var masculino = findViewById<RadioButton>(R.id.rd_masculino)
        var feminino = findViewById<RadioButton>(R.id.rd_feminino)

        if (validFields(name, cpf, nascimento, masculino, feminino)) {

        }

    }
}