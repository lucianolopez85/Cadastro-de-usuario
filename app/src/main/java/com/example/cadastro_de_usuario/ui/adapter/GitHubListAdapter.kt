package com.example.cadastro_de_usuario.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_repository.view.*

class GitHubListAdapter(
    private val repositoryList: List<GitHubListVO> = ArrayList()
) : RecyclerView.Adapter<GitHubListAdapter.AdapterViewHolder>() {

    private val picasso: Picasso by lazy { Picasso.get() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return AdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.textName.text = repositoryList[position].name
        holder.textdescription.text = repositoryList[position].description
        holder.numberStart.text = repositoryList[position].star
        holder.numberFork.text = repositoryList[position].fork
        setupPicasso(holder.imageAvatar, repositoryList[position].avatar)
    }

    private fun setupPicasso(view: ImageView, avatar: String) {
        picasso
            .load(avatar)
            .fit()
            .into(view)
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