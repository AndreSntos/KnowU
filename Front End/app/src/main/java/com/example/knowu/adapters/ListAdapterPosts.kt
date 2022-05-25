package com.example.knowu.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.knowu.HomeFragment
import com.example.knowu.R
import com.example.knowu.activities.HomeEvent
import com.example.knowu.model.Postagem

class ListAdapterPosts(private val context: FragmentActivity, private val postagens: ArrayList<Postagem>) :
    ArrayAdapter<Postagem>(context, R.layout.list_item_posts, postagens) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item_posts, null)

        val tvNomeUsuario: TextView = view.findViewById(R.id.tvNomeUsuario)
        val tvUsuario: TextView = view.findViewById(R.id.tvUsuario)
        val etPostagem: EditText = view.findViewById(R.id.etPostagemUsuario)
        val ivImagemUsuario: ImageView = view.findViewById(R.id.ivImagemUsuario)
        val ivPostagemCurtida: ImageView = view.findViewById(R.id.ivPostagemCurtida)

        tvNomeUsuario.setText(postagens[position].usuario.nome)
        tvUsuario.setText(postagens[position].usuario.usuario)
        etPostagem.setText(postagens[position].postagem)
        ivImagemUsuario.setImageResource(postagens[position].imagem)
        if (postagens[position].curtida) {
            ivPostagemCurtida.setImageResource(R.drawable.favorite_withbackground)
        } else ivPostagemCurtida.setImageResource(R.drawable.favorite_nobackground_posts)


        return view
    }
}