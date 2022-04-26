package com.example.knowu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R
import com.example.knowu.request.UsuarioAdicionarRequest
import com.example.knowu.rest.Rest
import com.example.knowu.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpTwoActivity : AppCompatActivity() {

    private lateinit var usuario: UsuarioAdicionarRequest;
    private val retrofit = Rest.getInstance()
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
        } else if (etCPF.text.toString().length > 14 || etCPF.text.toString().isEmpty()) {
            etCPF.error = "Digite um cpf válido!"
            return false;
        } else if (etNascimento.text.toString().isEmpty() || etNascimento.text.toString()
                .isBlank()
        ) {
            etNascimento.error = "Digite uma data de nascimento válida!"
            return false;
        } else if (!rdMasculino.isChecked && !rdFeminino.isChecked) {
            rdMasculino.error = "Selecione um genero!"
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
            usuario = UsuarioAdicionarRequest(
                name.text.toString(),
                intent.getStringExtra("user").toString(),
                intent.getStringExtra("email").toString(),
                cpf.text.toString(),
                nascimento.text.toString(),
                if (masculino.isChecked) "Masculino" else "Feminino",
                intent.getStringExtra("password").toString()
            )

            val requestAPI = retrofit.create(UsuarioService::class.java)
            requestAPI.adicionar(usuario).enqueue(object: Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.code() == 201) {
                        returnToLogin()
                    }
                    println(response)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println(t.message + call.toString())
                    Toast.makeText(baseContext, "Não foi possível se conectar a aplicação", Toast.LENGTH_LONG).show()
                }
            })
        }

    }

    fun generoSelecionado(view: View) {

        when (view.id) {
            R.id.rd_masculino -> {
                findViewById<RadioButton>(R.id.rd_masculino).isChecked = true
                findViewById<RadioButton>(R.id.rd_feminino).isChecked = false
            }
            else -> {
                findViewById<RadioButton>(R.id.rd_masculino).isChecked = false
                findViewById<RadioButton>(R.id.rd_feminino).isChecked = true
            }
        }
    }

    fun returnToLogin() {
        startActivity(Intent(baseContext, SignInActivity::class.java))
    }
}