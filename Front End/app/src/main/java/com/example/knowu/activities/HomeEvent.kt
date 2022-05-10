package com.example.knowu.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R
import com.example.knowu.adapters.ListAdapterPosts
import com.example.knowu.model.LocalizacaoEvento
import com.example.knowu.model.Postagem
import com.example.knowu.rest.Rest
import com.example.knowu.services.CEPService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeEvent : AppCompatActivity() {

    private lateinit var postagens: ArrayList<Postagem>

    val baseUrl = "http://34.228.172.224:8080/"
    private val retrofit = Rest.getInstance(baseUrl)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_event)
        buscarPostagens()
    }

    fun buscarPostagens() {

        val ivImagemUsuario: IntArray = intArrayOf(
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

        for (i in tvNomeUsuario.indices) {
            val postagem =
                Postagem(tvNomeUsuario[i], tvUsuario[i], ivImagemUsuario[i], etPostagem[i], false)
            postagens.add(postagem)
        }

        val lvListaPostagem: ListView = findViewById(R.id.lvListaPostagem)
        lvListaPostagem.layoutParams.height = 700 * tvNomeUsuario.size
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