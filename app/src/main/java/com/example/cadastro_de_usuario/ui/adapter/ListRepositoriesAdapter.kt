package com.example.cadastro_de_usuario.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.databinding.ItemRepositoryBinding
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import com.squareup.picasso.Picasso

internal class ListRepositoriesAdapter:
    PagingDataAdapter<GitHubListVO, ListRepositoriesAdapter.AdapterViewHolder>(COMPARATOR) {

    inner class AdapterViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemRepositoryBinding.bind(view)
        private val picasso: Picasso by lazy { Picasso.get() }

        fun bind(itemView: GitHubListVO) = with(binding) {
            textRepositoryName.text = itemView.nameRepository
            textDescription.text = itemView.description
            textStar.text = itemView.star
            textFork.text = itemView.fork
            setImageView(avatar, itemView.avatar)
        }

        fun setImageView(view: ImageView, avatar: String) {
            picasso
                .load(avatar)
                .fit()
                .into(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder =
        AdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        )

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GitHubListVO>() {
            override fun areItemsTheSame(oldItem: GitHubListVO, newItem: GitHubListVO): Boolean {
                return oldItem.nameRepository == newItem.nameRepository
            }

            override fun areContentsTheSame(oldItem: GitHubListVO, newItem: GitHubListVO): Boolean {
                return oldItem == newItem
            }
        }
    }
}
