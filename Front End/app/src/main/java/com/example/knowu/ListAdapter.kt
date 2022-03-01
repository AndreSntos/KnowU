package com.example.knowu

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(private val context: Activity, private val eventos: ArrayList<Evento>) :
    ArrayAdapter<Evento>(context, R.layout.list_item, eventos) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item, null)

        val tvIdEvento: TextView = view.findViewById(R.id.tvIdEvento)
        val ivFundo: ImageView = view.findViewById(R.id.ivFundo)
        val tvTitulo: TextView = view.findViewById(R.id.tvTitulo)
        val tvDescricao: TextView = view.findViewById(R.id.tvDescricao)

        tvIdEvento.setText(eventos[position].id.toString())
        ivFundo.setImageResource(eventos[position].imagem)
        tvTitulo.setText(eventos[position].titulo)
        tvDescricao.setText(eventos[position].descricao)

        return view
    }


}