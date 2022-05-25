package com.example.knowu

import android.content.Context
import android.content.Intent
import android.media.metrics.Event
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.FragmentActivity
import com.example.knowu.activities.CriarEventoActivity
import com.example.knowu.activities.EventActivity
import com.example.knowu.activities.EventClickedActivity
import com.example.knowu.activities.MapsActivity
import com.example.knowu.adapters.ListAdapterEvents
import com.example.knowu.adapters.ListAdapterPosts
import com.example.knowu.model.Evento
import com.example.knowu.model.EventoFragment
import com.example.knowu.model.Postagem
import com.example.knowu.rest.Rest
import com.example.knowu.services.EventoService
import com.example.knowu.services.PostagemService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventoFragment : Fragment() {

    private lateinit var eventos: ArrayList<Evento>
    private lateinit var view2: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view2 = inflater.inflate(R.layout.fragment_evento, container, false)
        buscarPostagens()
        val lvListaEvento: ListView = view2.findViewById(R.id.lvListaEvento)
        lvListaEvento.setOnItemClickListener { parent, _, position, _ ->
            val evento: Evento = parent.getItemAtPosition(position) as Evento

            val intent = Intent(activity, EventClickedActivity::class.java)
            intent.putExtra("idEvento", evento.id)
            intent.putExtra("titleEvent", evento.titulo)
            intent.putExtra("describeEvent", evento.descricao)
            intent.putExtra("imageEvent", evento.imagem)
            startActivity(intent)
        }
        return view2;
    }

    fun buscarPostagens() {

        val ivImagemUsuario: IntArray = intArrayOf(
            R.drawable.event_logo, R.drawable.event_logo,
            R.drawable.event_logo, R.drawable.event_logo
        )
        eventos = ArrayList()

        val user = this.activity?.getSharedPreferences(
            "USER",
            Context.MODE_PRIVATE
        )

        var baseUrl= "http://10.18.35.141:8080/evento/"
        val retrofit = Rest.getInstance(baseUrl)
        val eventoRequest = retrofit.create(EventoService::class.java)

        eventoRequest.list().enqueue(object :
            Callback<List<EventoFragment>> {
            override fun onResponse(
                call: Call<List<EventoFragment>>,
                response: Response<List<EventoFragment>>
            ) {
                if (response.code() == 200) {


                    val eventosEncontradas: List<EventoFragment>? = response.body()
                    val ivImagemEvento: IntArray = intArrayOf(
                        R.drawable.event_logo, R.drawable.event_logo,
                        R.drawable.event_logo, R.drawable.event_logo
                    )
                    if (eventosEncontradas?.size!! > 0) {

                        for (i in eventosEncontradas.indices) {

                            eventos.add(Evento(
                                eventosEncontradas[i].id,
                                eventosEncontradas[i].nome,
                                ivImagemEvento[0],
                                eventosEncontradas[i].descricao
                            ))

                        }
                        inserirEventosNoAdapter()

                    }
                }

            }
            override fun onFailure(call: Call<List<EventoFragment>>, t: Throwable) {
                println("ERROR" + t.message)
            }
        })
    }

    fun inserirEventosNoAdapter() {

        val lvListaEvento: ListView = view2.findViewById(R.id.lvListaEvento)
        lvListaEvento.adapter = ListAdapterEvents(view?.context as FragmentActivity, eventos)
    }

}