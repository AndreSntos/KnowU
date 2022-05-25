package com.example.knowu.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R
import com.example.knowu.adapters.ListAdapterPosts
import com.example.knowu.model.EventoFragment
import com.example.knowu.model.Postagem
import com.example.knowu.rest.Rest
import com.example.knowu.services.PostagemService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventClickedActivity : AppCompatActivity() {

    private lateinit var postagens: ArrayList<Postagem>

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_clicked)
        postagens = ArrayList()
        val id = intent.getIntExtra("idEvento", 0)
        val title = intent.getStringExtra("titleEvent")
        val describe = intent.getStringExtra("describeEvent")
        val image = intent.getIntExtra("imageEvent", 0)
//        findViewById<BottomNavigationItemView>(R.id.page_2).setBackgroundColor(Color.WHITE)
//        findViewById<BottomNavigationItemView>(R.id.page_3).setBackgroundColor(Color.WHITE)
//        findViewById<BottomNavigationItemView>(R.id.page_4).setBackgroundColor(Color.WHITE)

        findViewById<TextView>(R.id.tvTitulo).text = title.toString()
        findViewById<TextView>(R.id.tvDescribe).text = describe.toString()
        findViewById<ImageView>(R.id.ivImage).setImageResource(image)
//        buscarPostagens()
        buscarPostagens()
        verificaSeUsuarioEstaNoEvento()

    }

    fun verificaSeUsuarioEstaNoEvento() {
        val user = getSharedPreferences(
            "USER",
            Context.MODE_PRIVATE
        )
        val idUsuario = user.getInt("id", 0)

    }


    fun buscarPostagens() {

        val ivImagemUsuario: IntArray = intArrayOf(
            R.drawable.event_logo, R.drawable.event_logo,
            R.drawable.event_logo, R.drawable.event_logo
        )
        postagens = ArrayList()

        val user = getSharedPreferences(
            "USER",
            Context.MODE_PRIVATE
        )
        val idUsuario = user.getInt("id", 0)
        val idEvento = intent.getIntExtra("idEvento", 6)

        var baseUrl= "http://192.168.0.188:8080/postagens/"
        val retrofit = Rest.getInstance(baseUrl)
        val postagemRequest = retrofit.create(PostagemService::class.java)

        postagemRequest.list(idEvento).enqueue(object :
            Callback<List<Postagem>> {
            override fun onResponse(
                call: Call<List<Postagem>>,
                response: Response<List<Postagem>>
            ) {
                if (response.code() == 200) {


                    val postagensEncontradas: List<Postagem>? = response.body()
                    val ivImagemUsuario: IntArray = intArrayOf(
                        R.drawable.event_logo, R.drawable.event_logo,
                        R.drawable.event_logo, R.drawable.event_logo
                    )
                    if (postagensEncontradas?.size!! > 0) {

                        for (i in postagensEncontradas.indices) {

                            postagens.add(Postagem(
                                    postagensEncontradas[i].postagem,
                                    postagensEncontradas[i].usuario,
                                    postagensEncontradas[i].evento,
                                    postagensEncontradas[i].dataPostagem,
                                    ivImagemUsuario[0],
                                    false
                                ))

                        }
                        inserirPostagensNoAdapter()

                    }
                }

            }
            override fun onFailure(call: Call<List<Postagem>>, t: Throwable) {
                println("ERROR" + t.message)
            }
        })
    }

    fun inserirPostagensNoAdapter() {
        val lvListaPostagem: ListView = findViewById(R.id.lvListaPostagem)
        lvListaPostagem.layoutParams.height = 800 * postagens.size
        lvListaPostagem.adapter = ListAdapterPosts(this, postagens)
    }


    fun voltar(view: View) {
        finish()
    }
}

