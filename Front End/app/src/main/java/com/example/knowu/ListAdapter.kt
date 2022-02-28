package com.example.knowu

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.viewbinding.ViewBindings

class ListAdapter(private val context: Activity, private val eventos: ArrayList<Evento>) :
    ArrayAdapter<Evento>(context, R.layout.list_item, eventos) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item, null)

        val ivFundo: ImageView = view.findViewById(R.id.ivFundo)
        val tvTitulo: TextView = view.findViewById(R.id.tvTitulo)
        val tvDescricao: TextView = view.findViewById(R.id.tvDescricao)

        ivFundo.setImageResource(eventos[position].imagem)
        tvTitulo.setText(eventos[position].titulo)
        tvDescricao.setText(eventos[position].descricao)


        return view
    }
}