package com.example.knowu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.example.knowu.activities.HomeEvent
import com.example.knowu.adapters.ListAdapterPosts
import com.example.knowu.model.Postagem

class HomeFragment : Fragment() {

    private lateinit var postagens: ArrayList<Postagem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

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

        val lvListaPostagem: ListView = requireView().findViewById(R.id.lvListaPostagem)
        lvListaPostagem.layoutParams.height = 700 * tvNomeUsuario.size
        lvListaPostagem.adapter = ListAdapterPosts(requireContext() as HomeEvent,postagens)

    }



}