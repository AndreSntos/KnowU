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

        val ivFundo: IntArray = intArrayOf(R.drawable.events_background, R.drawable.event_logo, R.drawable.events_background, R.drawable.event_logo)
        val tvTitulo: Array<String> = arrayOf("Teste", "Teste 2", "Malla", "Sen")

        eventos = ArrayList()

        for (i in tvTitulo.indices) {
            val evento: Evento = Evento(tvTitulo[i], ivFundo[i])
            eventos.add(evento)
        }

        binding.lvListaEvento.adapter = ListAdapter(this, eventos)


    }

}