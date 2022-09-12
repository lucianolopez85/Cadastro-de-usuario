package com.example.cadastro_de_usuario.ui.adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.DataBaseSQLite
import com.example.cadastro_de_usuario.data.User

class ListAdapter(
    private var userList: List<User>
) : RecyclerView.Adapter<ListAdapter.AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return AdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.textName.text = userList[position].name

        holder.textEdit.setOnClickListener {
            val dialogAlertDialog = AlertDialog.Builder(it.rootView.context)
            with(dialogAlertDialog) {
                setTitle("Editar Contato")
                setMessage("id: ${userList[position].id} \nNome: ${userList[position].name}")
                setPositiveButton("ok") {_,_ -> }
                show()
            }
        }

        holder.textDelete.setOnClickListener {
            val dialogAlertDialog = AlertDialog.Builder(it.rootView.context)
            with(dialogAlertDialog) {
                setTitle("Excluir Contato")
                setMessage("id: ${userList[position].id} \nNome: ${userList[position].name}")
                setPositiveButton("ok") {_,_ -> }
                show()
            }
        }
    }

    override fun getItemCount() = userList.size

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.text_item_name)
        val textEdit = itemView.findViewById<TextView>(R.id.text_item_editar)
        val textDelete = itemView.findViewById<TextView>(R.id.text_item_excluir)
    }
}
