package com.example.knowu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.example.knowu.R
import com.example.knowu.model.Usuario
import com.example.knowu.request.UsuarioRequest
import com.example.knowu.rest.Rest
import com.example.knowu.services.UsuarioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SingInActivity : AppCompatActivity() {

    private val retrofit = Rest.getInstance()

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
            val usuarioRequest = retrofit.create(UsuarioService::class.java)
            usuarioRequest.login(UsuarioRequest(loginUser.text.toString(), passwordUser.text.toString())).enqueue(object : Callback<List<Usuario>> {
                override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {

                    startActivity(Intent(baseContext, MapsActivity::class.java))
                }

                override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                    var dialog = AlertDialog.Builder(baseContext);
                    dialog.setTitle("Erro").setIcon(R.drawable.error)
                        .setPositiveButton("Ok", null)
                    dialog.setMessage("Credências inválidas, tente novamente!!").show()

                }
            })
        } else {

        }
    }
}