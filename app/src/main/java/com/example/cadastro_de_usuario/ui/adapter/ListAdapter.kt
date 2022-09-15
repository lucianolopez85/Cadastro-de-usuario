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
import com.example.cadastro_de_usuario.databinding.DialogAlertDeleteBinding
import com.example.cadastro_de_usuario.databinding.DialogAlertEditBinding
import com.example.cadastro_de_usuario.databinding.DialogAlertUserBinding

class ListAdapter(
    private var userList: List<User>,
    private var updatedList: ((List<User>) -> Unit)
) : RecyclerView.Adapter<ListAdapter.AdapterViewHolder>() {

    lateinit var dataBaseSQLite: DataBaseSQLite
    private lateinit var dialog: AlertDialog

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false)
        return AdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.textName.text = userList[position].name

        holder.textName.setOnClickListener { view ->
            showUser(view, position)
        }

        holder.textDelete.setOnClickListener { view ->
            deleteUser(view, position)
        }

        holder.textEdit.setOnClickListener { view ->
            editUser(view, position )
        }
    }

    override fun getItemCount() = userList.size

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.text_item_name)
        val textEdit = itemView.findViewById<TextView>(R.id.text_item_editar)
        val textDelete = itemView.findViewById<TextView>(R.id.text_item_excluir)
    }

    fun showUser(view: View, position: Int) {
        val build = AlertDialog.Builder(view.context, R.style.ThemeCustomDialog)
        val dialogBinding = DialogAlertUserBinding
            .inflate(LayoutInflater.from(view.context))
        dialogBinding.textDialogName.text = userList[position].name
        dialogBinding.textDialogEmail.text = userList[position].email
        dialogBinding.textDialogSenha.text = userList[position].password
        build.setView(dialogBinding.root)
        dialogBinding.iconClose.setOnClickListener { dialog.dismiss() }
        dialog = build.create()
        dialog.show()
    }

    fun deleteUser(view: View, position: Int) {
        val build = AlertDialog.Builder(view.context, R.style.ThemeCustomDialog)
        val dialogBinding = DialogAlertDeleteBinding
            .inflate(LayoutInflater.from(view.context))
        dialogBinding.textDialogName.text = userList[position].name

        build.setView(dialogBinding.root)
        dialogBinding.buttonDialogDelete.setOnClickListener {
            dataBaseSQLite = DataBaseSQLite(it.context)
            dataBaseSQLite.delUser(userList[position].id)
            dialog.dismiss()
        }
        dialog = build.create()
        dialog.show()
    }

    fun editUser(view: View, position: Int) {
        val build = AlertDialog.Builder(view.context, R.style.ThemeCustomDialog)
        val dialogBinding = DialogAlertEditBinding
            .inflate(LayoutInflater.from(view.context))
        dialogBinding.buttonDialogEdit.setOnClickListener {
            dataBaseSQLite = DataBaseSQLite(it.context)
            dataBaseSQLite.updateUser(
                User(
                    id = userList[position].id,
                    name = dialogBinding.editDialogName.text.toString(),
                    email = dialogBinding.editDialogEmail.text.toString(),
                    password = dialogBinding.editDialogSenha.text.toString(),
                )
            )
            updatedList(dataBaseSQLite.getListUser())
            dialog.dismiss()
        }


        build.setView(dialogBinding.root)
        dialog = build.create()
        dialog.show()
    }

}
