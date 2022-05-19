package com.example.knowu.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R
import com.example.knowu.model.LocalizacaoEvento
import com.example.knowu.model.googleapi.Root
import com.example.knowu.request.EventoAdicionarRequest
import com.example.knowu.request.Localidade
import com.example.knowu.rest.Rest
import com.example.knowu.services.CEPService
import com.example.knowu.services.EventoService
import com.example.knowu.services.MetricasService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CriarEventoActivity : AppCompatActivity() {


    private lateinit var localizacaoEvento: LocalizacaoEvento


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar_evento)

    }

    fun criarEvento(view: View) {
        var baseUrl = "https://maps.google.com/maps/api/geocode/"
        val retrofit = Rest.getInstance(baseUrl)
        val metricasRequest = retrofit.create(MetricasService::class.java)

        val endereco: EditText = findViewById(R.id.tvEndereco)
        var enderecoAjustado: String = endereco.text.toString()
        enderecoAjustado = enderecoAjustado.replace(" ", "+")

        val bairro: EditText = findViewById(R.id.tvBairro)
        var bairroAjustado: String = bairro.text.toString()
        bairroAjustado = bairroAjustado.replace(" ", "+")
        enderecoAjustado += "+" + findViewById<EditText>(R.id.tvNumero).text.toString()
        enderecoAjustado += "," + "+" + bairroAjustado + "," + localizacaoEvento.state

        println(enderecoAjustado)

        metricasRequest.list(enderecoAjustado, "BR", "AIzaSyCZsNXNO7Y36v1Nw8AmM8-LacW75MhISC8")
            .enqueue(object :
                Callback<Root> {
                override fun onResponse(
                    call: Call<Root>,
                    resp: Response<Root>
                ) {
                    val lat: Double? = resp.body()?.results?.get(0)?.geometry?.bounds?.northeast?.lat
                    val lng: Double? = resp.body()?.results?.get(0)?.geometry?.bounds?.northeast?.lng
                    baseUrl = "http://34.228.172.224:8080/evento/"
                    val retrofit = Rest.getInstance(baseUrl)
                    val user = getSharedPreferences(
                        "USER",
                        Context.MODE_PRIVATE
                    )
                    val eventoRequest = retrofit.create(EventoService::class.java)
                    val id = user.getInt("id", 0)
                    if (resp.body()?.status.equals("OK")) {
                        val localidade = Localidade(
                            enderecoAjustado.replace("+", " "),
                            lat!!,
                            lng!!
                        )
                        println(localidade.endereco + localidade.latitute + localidade.longitute)
                        eventoRequest.add(
                            id,
                            EventoAdicionarRequest(
                                findViewById<EditText>(R.id.tvNome_evento).text.toString(),
                                findViewById<EditText>(R.id.tvDescricao).text.toString(),
                                localidade
                            )
                        ).enqueue(object :
                            Callback<Void> {
                            override fun onResponse(
                                call: Call<Void>,
                                response: Response<Void>
                            ) {
                                if (response.code() == 201) {
                                    val dataEvento: EditText = findViewById(R.id.tvDataEvento)
                                    println(findViewById<EditText>(R.id.tvHoraInicioEvento).text.toString())
                                    println(findViewById<EditText>(R.id.tvHoraFimEvento).text.toString())
                                    val intent = Intent(Intent.ACTION_INSERT)
                                    intent.type = "vnd.android.cursor.item/event"
                                    intent.putExtra(CalendarContract.Events.TITLE, findViewById<EditText>(R.id.tvNome_evento).text.toString());
                                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, localidade.endereco);
                                    intent.putExtra(CalendarContract.Events.DESCRIPTION, findViewById<EditText>(R.id.tvDescricao).text.toString());

                                    val calDate = GregorianCalendar(
                                        2022,
                                        4,
                                        12
                                    )
                                    intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                                        23.00
                                    );
                                    intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                                        calDate.timeInMillis);

                                    intent.data = CalendarContract.Events.CONTENT_URI
                                    startActivity(intent)

                                }


                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                println("ERROR" + t.message)
                            }
                        })
                    }


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
        var nome_evento = findViewById<EditText>(R.id.tvNome_evento);
        var descricao = findViewById<EditText>(R.id.tvDescricao);
        var dialog = AlertDialog.Builder(this);
        dialog.setTitle("Atenção").setIcon(R.drawable.error)
            .setPositiveButton("Ok", null)
        return true;
    }

    fun irTelaSucesso() {
        startActivity(Intent(baseContext, SucessoActivity::class.java))
    }
}