package com.example.knowu.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ListView
import com.example.knowu.R
import com.example.knowu.adapters.ListAdapterInterests
import com.example.knowu.model.Interesses


class InterestsActivity : AppCompatActivity() {
    private lateinit var interests: ArrayList<Interesses>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interests)


        val tvIdInteresse: IntArray = intArrayOf(1, 2, 3, 4)

        val ivFundo: IntArray = intArrayOf(
            R.drawable.art, R.drawable.music,
            R.drawable.winner, R.drawable.tecnology
        )

        val ivUsers: IntArray = intArrayOf(
            R.drawable.persons_interests, R.drawable.person_interests_2,
            R.drawable.person_interests_3, R.drawable.pernson_interests_4
        )


        val tvTitulo: Array<String> = arrayOf("Artes", "Cultura Pop", "Esporte", "Tecnologia")

        val tvCountUsers: Array<String> = arrayOf(
            "400+ outros usuários",
            "1200+ outros usuários",
            "700+ outros usuários",
            "200+ outros usuários"
        )

        interests = ArrayList()

        for (i in tvTitulo.indices) {
            val interest =
                Interesses(
                    tvIdInteresse[i],
                    tvTitulo[i],
                    tvCountUsers[i],
                    ivFundo[i],
                    ivUsers[i],
                )
            interests.add(interest)
        }

        val lvListaInteresses: ListView = findViewById(R.id.lvListaInteresses)
        lvListaInteresses.adapter = ListAdapterInterests(this, interests)

        lvListaInteresses.setOnItemClickListener { parent, _, position, _ ->
            val interesse: Interesses = parent.getItemAtPosition(position) as Interesses
            println("Id Interesse Coletado ${interesse.id}")

            var t = LinearLayout(baseContext)
            t = findViewById(R.id.ivBackground)
            if (t.hasOnClickListeners()) {
                t.setBackgroundResource(R.color.design_default_color_error)
            }


            val intent = Intent(this, EventActivity::class.java)
//            intent.putExtra("idEvento", interesse.id)
//            intent.putExtra("titleEvent", interesse.titulo)
//            intent.putExtra("describeEvent", interesse.contagemUsuarios)
//            intent.putExtra("imageEvent", interesse.imagemUsuarios)
//            intent.putExtra("imageEvent", interesse.imagem)
            startActivity(intent)
        }


    }

    fun otherColor(v: View){

    }



}