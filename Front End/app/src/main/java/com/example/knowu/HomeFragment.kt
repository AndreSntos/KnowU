package com.example.knowu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.knowu.activities.CriarEventoActivity
import com.example.knowu.activities.EventActivity
import com.example.knowu.activities.MapsActivity
import com.example.knowu.adapters.ListAdapterPosts
import com.example.knowu.model.Postagem
import com.example.knowu.rest.Rest
import com.example.knowu.services.PostagemService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var postagens: ArrayList<Postagem>
    private lateinit var view2: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        view2 = inflater.inflate(R.layout.fragment_home, container, false)
        buscarPostagens()
        val floatingActionButton: FloatingActionButton = view2.findViewById(R.id.floatingActionButton3)
        floatingActionButton.setOnClickListener {
            startActivity(
                Intent(
                    view2.context,
                    CriarEventoActivity::class.java
                )
            )
        }
        val fabMaps: FloatingActionButton = view2.findViewById(R.id.fabMaps)
        fabMaps.setOnClickListener {
            startActivity(
                Intent(
                    view2.context,
                    MapsActivity::class.java
                )
            )
        }
        return view2;
    }

    fun buscarPostagens() {

        val ivImagemUsuario: IntArray = intArrayOf(
            R.drawable.event_logo, R.drawable.event_logo,
            R.drawable.event_logo, R.drawable.event_logo
        )
        postagens = ArrayList()

        val user = this.activity?.getSharedPreferences(
            "USER",
            Context.MODE_PRIVATE
        )

        val idUsuario = user?.getInt("id", 0)
        val intent = Intent(activity, HomeFragment::class.java)
        val idEvento = intent.getIntExtra("idEvento", 1)

        var baseUrl= "http://10.18.35.141:8080/postagens/"
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

        val lvListaPostagem: ListView = view2.findViewById(R.id.lvListaPostagem)
        lvListaPostagem.layoutParams.height = 700 * postagens.size
        lvListaPostagem.adapter = ListAdapterPosts(view?.context as FragmentActivity, postagens)
    }


    fun voltar(view: View) {
        startActivity(Intent(view.context, EventActivity::class.java))
    }

}