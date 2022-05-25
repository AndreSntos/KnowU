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


class ProfileActivity : AppCompatActivity() {
    private lateinit var postagens: ArrayList<Postagem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
//        buscarPostagens()
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

        // for (i in tvNomeUsuario.indices) {
        // val postagem =
        //   Postagem(tvNomeUsuario[i], tvUsuario[i], ivImagemUsuario[i], etPostagem[i], false)
        // postagens.add(postagem)
        // }

        val lvListaPostagem: ListView = findViewById(R.id.lvListaPostagem)
        lvListaPostagem.layoutParams.height = 700 * tvNomeUsuario.size
        lvListaPostagem.adapter = ListAdapterPosts(this, postagens)

    }


    fun goToFaq(view: View) {
        startActivity(Intent(baseContext, FaqActivity::class.java))
    }

}