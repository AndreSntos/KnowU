package com.example.knowu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.knowu.databinding.ActivityEventBinding

class EventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventBinding
    private lateinit var eventos: ArrayList<Evento>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            val evento: Evento = Evento(tvTitulo[i], ivFundo[i], tvDescricao[i])
            eventos.add(evento)
        }

        binding.lvListaEvento.adapter = ListAdapter(this, eventos)


    }

}