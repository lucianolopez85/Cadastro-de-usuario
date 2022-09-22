package com.example.cadastro_de_usuario.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.domain.vo.GitHubRepositoryVO

import kotlinx.android.synthetic.main.item_repository.view.*

class GitHubRepositoryAdapter(
    private val repositoryList: List<GitHubRepositoryVO> = ArrayList()
) : RecyclerView.Adapter<GitHubRepositoryAdapter.AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return AdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.imageAvatar.setImageResource(repositoryList[position].avatar)
        holder.textName.text = repositoryList[position].name
        holder.textdescription.text = repositoryList[position].description
        holder.numberStart.text = repositoryList[position].star
        holder.numberFork.text = repositoryList[position].fork
    }

    override fun getItemCount(): Int  = repositoryList.size

    class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageAvatar = itemView.avatar
        val textName = itemView.text_repository_name
        val textdescription = itemView.text_description
        val numberStart = itemView.text_star
        val numberFork = itemView.text_fork
    }

}