package com.example.knowu.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R
import com.example.knowu.model.LocalizacaoEvento
import com.example.knowu.model.googleapi.Root
import com.example.knowu.rest.Rest
import com.example.knowu.services.CEPService
import com.example.knowu.services.MetricasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CriarEventoActivity : AppCompatActivity() {


    private lateinit var localizacaoEvento: LocalizacaoEvento


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_evento)

    }

    fun criarEvento(view: View) {
        val baseUrl = "https://maps.google.com/maps/api/geocode/"
        val retrofit = Rest.getInstance(baseUrl)
        val metricasRequest = retrofit.create(MetricasService::class.java)

        val endereco: EditText = findViewById(R.id.tvEndereco)
        var enderecoAjustado: String = endereco.text.toString()
        enderecoAjustado = enderecoAjustado.replace(" ", "+")

        val bairro: EditText = findViewById(R.id.tvBairro)
        var bairroAjustado: String = bairro.text.toString()
        bairroAjustado = bairroAjustado.replace(" ", "+")
        enderecoAjustado += "+" + findViewById<EditText>(R.id.tvNumero).text.toString()
        enderecoAjustado += "," +  "+" + bairroAjustado + "," + localizacaoEvento.state

        println(enderecoAjustado)

        metricasRequest.list(enderecoAjustado, "BR", "AIzaSyCZsNXNO7Y36v1Nw8AmM8-LacW75MhISC8").enqueue(object :
            Callback<Root> {
            override fun onResponse(
                call: Call<Root>,
                response: Response<Root>
            ) {

                println("AAAAAA" + response.body()?.results?.get(0)?.geometry?.bounds?.northeast?.lat + " " + response.body()?.results?.get(0)?.geometry?.bounds?.northeast )


            }

            override fun onFailure(call: Call<Root>, t: Throwable) {
                println("ERROR" + t.message)
            }
        })
    }


    fun pesquisarCEP(view: View) {
        val baseUrl = "https://ws.apicep.com"
        val retrofit = Rest.getInstance(baseUrl)
        val cepRequest = retrofit.create(CEPService::class.java)
        cepRequest.list(findViewById<EditText>(R.id.tvCEP).text.toString()).enqueue(object :
            Callback<LocalizacaoEvento> {
            override fun onResponse(
                call: Call<LocalizacaoEvento>,
                response: Response<LocalizacaoEvento>
            ) {

                localizacaoEvento = response.body() as LocalizacaoEvento
                findViewById<TextView>(R.id.tvEndereco).text = response.body()?.address.toString()
                findViewById<TextView>(R.id.tvBairro).text = response.body()?.district.toString()


            }

            override fun onFailure(call: Call<LocalizacaoEvento>, t: Throwable) {
                println("ERROR" + t.message)
            }
        })
    }

    fun validarCampos(): Boolean {
        var nome_evento = findViewById<EditText>(R.id.ti_nome_evento);
        var descricao = findViewById<EditText>(R.id.ti_descricao);
        var dialog = AlertDialog.Builder(this);
        dialog.setTitle("Atenção").setIcon(R.drawable.error)
            .setPositiveButton("Ok", null)
        return true;
    }

    fun irTelaSucesso() {
        startActivity(Intent(baseContext, SucessoActivity::class.java))
    }
}