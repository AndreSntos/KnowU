package com.example.knowu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.databinding.ActivityEventBinding
import org.json.JSONArray
import org.json.JSONObject


class EventActivity: AppCompatActivity() {

    private lateinit var eventos: ArrayList<Evento>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        val tvIdEvento: IntArray = intArrayOf(1, 2, 3, 4)

        val ivFundo: IntArray = intArrayOf(
            R.drawable.music_background_events, R.drawable.shopping_background_events,
            R.drawable.park_background_events, R.drawable.soccer_background_events
        )
        val tvTitulo: Array<String> = arrayOf("Música", "Shopping", "Parque", "Futebol")

        val tvDescricao: Array<String> = arrayOf(
            "Evento que ocorrerá no sábado  no teatro municipal ",
            "Evento que ocorrerá no shopping Tatuapé",
            "Um dia divertido no Ibirapuera para correr.",
            "Um futebol que vai acontecer na bandtec"
        )

        eventos = ArrayList()

        for (i in tvTitulo.indices) {
            val evento = Evento(tvIdEvento[i], tvTitulo[i], ivFundo[i], tvDescricao[i])
            eventos.add(evento)
        }

        val lvListaEvento: ListView = findViewById(R.id.lvListaEvento)
        lvListaEvento.adapter = ListAdapter(this, eventos)

        lvListaEvento.setOnItemClickListener { parent, _, position, _ ->
            val evento: Evento = parent.getItemAtPosition(position) as Evento
            println("Id Evento Coletado ${evento.id}")

            val intent = Intent(this, EventClickedActivity::class.java)
            intent.putExtra("idEvento", evento.id)
            startActivity(intent)
        }


    }
}

