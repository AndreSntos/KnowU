package com.example.knowu.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.knowu.model.Evento
import com.example.knowu.R
import com.example.knowu.model.Interesses

class ListAdapterInterests(private val context: Activity, private val interesses: ArrayList<Interesses>) :
    ArrayAdapter<Interesses>(context, R.layout.list_item, interesses) {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.list_item_interests, null)

        val tvIdEvento: TextView = view.findViewById(R.id.tvIdInteresses)
        val ivFundo: ImageView = view.findViewById(R.id.ivFundo)
        val ivUsers: ImageView = view.findViewById(R.id.iv_user_interests)
        val tvCountUser: TextView = view.findViewById(R.id.tv_count_users)
        val tvTitulo: TextView = view.findViewById(R.id.tv_titulo_interests)

        tvIdEvento.setText(interesses[position].id.toString())
        tvCountUser.setText(interesses[position].contagemUsuarios)
        ivFundo.setImageResource(interesses[position].imagem)
        ivUsers.setImageResource(interesses[position].imagemUsuarios)
        tvTitulo.setText(interesses[position].titulo)

        return view
    }


}