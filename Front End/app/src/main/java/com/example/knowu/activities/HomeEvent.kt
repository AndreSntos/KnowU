package com.example.knowu.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R
import com.example.knowu.adapters.ListAdapterPosts
import com.example.knowu.model.Postagem
import com.example.knowu.rest.Rest

class HomeEvent : AppCompatActivity() {

    private lateinit var eventos: ArrayList<Evento>
    private lateinit var postagens: ArrayList<Postagem>

    //val baseUrl = "http://192.168.0.10:8080/"
    //private val retrofit = Rest.getInstance(baseUrl)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_event)

        setContentView(R.layout.activity_event_clicked)
        postagens = ArrayList()
        eventos = ArrayList()
        val tvIdEvento: IntArray = intArrayOf(1, 2, 3, 4)
        val tvTitulo: Array<String> = arrayOf("Música", "Shopping", "Parque", "Futebol")

        val ivFundo: IntArray = intArrayOf(
            R.drawable.music_background_events, R.drawable.shopping_background_events,
            R.drawable.park_background_events, R.drawable.soccer_background_events
        )
        val tvDescricao: Array<String> = arrayOf(
            "Evento que ocorrerá no sábado  no teatro municipal ",
            "Evento que ocorrerá no shopping Tatuapé",
            "Um dia divertido no Ibirapuera para correr.",
            "Um futebol que vai acontecer na bandtec"
        )

        for (i in tvTitulo.indices) {
            val evento = Evento(tvIdEvento[i], tvTitulo[i], ivFundo[i], tvDescricao[i])
            eventos.add(evento)
        }

        val lvListaEvento: ListView = findViewById(R.id.lvListaEvento)
        lvListaEvento.adapter = ListAdapterEvents(this, eventos)

        //Referente ao EventClicked
        //val id = intent.getIntExtra("idEvento", 0)
        //val title = intent.getStringExtra("titleEvent")
        //val describe = intent.getStringExtra("describeEvent")
        //val image = intent.getIntExtra("imageEvent", 0)
        //findViewById<BottomNavigationItemView>(R.id.page_2).setBackgroundColor(Color.WHITE)
        //findViewById<BottomNavigationItemView>(R.id.page_3).setBackgroundColor(Color.WHITE)
        //findViewById<BottomNavigationItemView>(R.id.page_4).setBackgroundColor(Color.WHITE)

        findViewById<TextView>(R.id.tvTitulo).text = title.toString()
        //findViewById<TextView>(R.id.tvDescribe).text = describe.toString()
        //findViewById<ImageView>(R.id.ivImage).setImageResource(image)

        buscarPostagens()
    }

    fun buscarPostagens() {

        val user = getSharedPreferences(
            "USER",
            Context.MODE_PRIVATE
        )
        //val idUsuario = user.getInt("id", 0)
        val idEvento = intent.getIntExtra("idEvento", 6)

        var baseUrl= "http://192.168.0.10:8080/postagens/"
        val retrofit = Rest.getInstance(baseUrl)
        val postagemRequest = retrofit.create(PostagemService::class.java)

        postagemRequest.list(idEvento).enqueue(object :
            Callback<List<Postagem>> {
            override fun onResponse(
                call: Call<List<Postagem>>,
                response: Response<List<Postagem>>
            ) {
                if (response.code() == 200) {

                    println(response.body())
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

    //Informações anteriores

    /*val ivImagemUsuario: IntArray = intArrayOf(
        R.drawable.user_profile, R.drawable.user_profile,
        R.drawable.user_profile, R.drawable.user_profile
    )
    val tvNomeUsuario: Array<String> =
        arrayOf("Anderson Leiva", "Patricia Silva", "Kevin Natali", "Vivian Lelis")

    val tvUsuario: Array<String> = arrayOf(
        "@leivaand",
        "@patsilva",
        "@kevinnatali",
        "@vivilelis"
    )

    val etPostagem: Array<String> = arrayOf(
        "Ótimo musical! Fantástico.",
        "Culto incrível, obrigado a todos que compareceram!",
        "Que jogada que eu fiz hoje! Valeu a pena voltar a ativa.",
        "Foi lindo rever esse museu com tanta história!"
    )

    postagens = ArrayList()

    // for (i in tvNomeUsuario.indices) {
       // val postagem =
         //   Postagem(tvNomeUsuario[i], tvUsuario[i], ivImagemUsuario[i], etPostagem[i], false)
        // postagens.add(postagem)
   // }

    val lvListaPostagem: ListView = findViewById(R.id.lvListaPostagem)
    lvListaPostagem.layoutParams.height = 700 * tvNomeUsuario.size
    lvListaPostagem.adapter = ListAdapterPosts(this, postagens)

    }*/



    fun inserirPostagensNoAdapter() {
        val lvListaPostagem: ListView = findViewById(R.id.lvListaPostagem)
        lvListaPostagem.layoutParams.height = 700 * postagens.size
        lvListaPostagem.adapter = ListAdapterPosts(this, postagens)
    }


    fun goMaps(view: View) {
        startActivity(Intent(baseContext, MapsActivity::class.java))
    }

    fun abrirCriacaoEvento(view: View) {
        startActivity(Intent(baseContext, CriarEventoActivity::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.page_1 -> {
                item.isChecked = true
                true
            }
            R.id.page_2 -> {
                item.isChecked = true
                true
            }
            R.id.page_3 -> {
                item.isChecked = true
                startActivity(Intent(baseContext, EventActivity::class.java))
                true
            }
            R.id.page_4 -> {
                item.isChecked = true
                startActivity(Intent(baseContext, ProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}