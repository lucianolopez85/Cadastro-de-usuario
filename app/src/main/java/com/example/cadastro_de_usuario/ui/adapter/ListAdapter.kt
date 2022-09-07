package com.example.cadastro_de_usuario.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.User

class ListAdapter(
    private val userList: ArrayList<User>
) : RecyclerView.Adapter<ListAdapter.AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return AdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val user = userList[position]
        holder.textName.text = user.name
        holder.textEmail.text = user.email
        holder.TextSenha.text = user.senha
        holder.TextTipo.text = user.tipo.toString()
    }

    override fun getItemCount() = userList.size

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.text_item_name)
        val textEmail = itemView.findViewById<TextView>(R.id.text_item_email)
        val TextSenha = itemView.findViewById<TextView>(R.id.text_item_senha)
        val TextTipo = itemView.findViewById<TextView>(R.id.text_item_tipo)
    }
}
