package com.example.knowu.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.R
import com.example.knowu.adapters.ListAdapterPosts
import com.example.knowu.model.Postagem

class EventClickedActivity : AppCompatActivity() {

    private lateinit var postagens: ArrayList<Postagem>

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_clicked)

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

    }


//    fun buscarPostagens() {
//
//        val ivImagemUsuario: IntArray = intArrayOf(
//            R.drawable.event_logo, R.drawable.event_logo,
//            R.drawable.event_logo, R.drawable.event_logo
//        )
//        val tvNomeUsuario: Array<String> =
//            arrayOf("Anderson Leiva", "Patricia Silva", "Kevin Natali", "Vivian Lelis")
//
//        val tvUsuario: Array<String> = arrayOf(
//            "@leivaand",
//            "@patsilva",
//            "@kevinnatali",
//            "@vivilelis"
//        )
//
//        val etPostagem: Array<String> = arrayOf(
//            "Ótimo musical! Fantástico.",
//            "Culto incrível, obrigado a todos que compareceram!",
//            "Que jogada que eu fiz hoje! Valeu a pena voltar a ativa.",
//            "Foi lindo rever esse museu com tanta história!"
//        )
//
//        postagens = ArrayList()
//
//        for (i in tvNomeUsuario.indices) {
//            val postagem =
//                Postagem(tvNomeUsuario[i], tvUsuario[i], ivImagemUsuario[i], etPostagem[i], false)
//            postagens.add(postagem)
//        }
//
//        val lvListaPostagem: ListView = findViewById(R.id.lvListaPostagem)
//        lvListaPostagem.layoutParams.height = 700 * tvNomeUsuario.size
//        lvListaPostagem.adapter = ListAdapterPosts(this, postagens)

//    }

//     @RequiresApi(Build.VERSION_CODES.O)
//     override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle item selection
//        return when (item.itemId) {
//            R.id.page_1 -> {
//                item.isChecked = true
//                startActivity(Intent(baseContext, HomeEvent::class.java))
//                true
//            }
//            R.id.page_2 -> {
//                item.isChecked = true
//                true
//            }
//            R.id.page_3 -> {
//                item.isChecked = true
//                startActivity(Intent(baseContext, EventActivity::class.java))
//                true
//            }
//            R.id.page_4 -> {
//                item.isChecked = true
//                startActivity(Intent(baseContext, ProfileActivity::class.java))
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    fun voltar(view: View) {
//        startActivity(Intent(baseContext, EventActivity::class.java))
//    }
}

