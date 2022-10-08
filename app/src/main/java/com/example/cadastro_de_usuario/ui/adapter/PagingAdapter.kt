package com.example.cadastro_de_usuario.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import com.example.cadastro_de_usuario.databinding.ItemRepositoryBinding
import com.squareup.picasso.Picasso

class PagingAdapter: PagingDataAdapter<GitHubRepositoryDTO, PagingAdapter.AdapterViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder =
        AdapterViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    inner class AdapterViewHolder(private val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {

        private val picasso: Picasso by lazy { Picasso.get() }

        fun bind(itemView: GitHubRepositoryDTO) {
            binding.root.setOnClickListener {
            }
            binding.textRepositoryName.text = itemView.name
            binding.textDescription.text = itemView.description
            binding.textStar.text = itemView.star.toString()
            binding.textFork.text = itemView.fork.toString()
            setImageView(binding.avatar, itemView.user.avatar)
        }

        fun setImageView(view: ImageView, avatar: String) {
            picasso
                .load(avatar)
                .fit()
                .into(view)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GitHubRepositoryDTO>() {
            override fun areItemsTheSame(oldItem: GitHubRepositoryDTO, newItem: GitHubRepositoryDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GitHubRepositoryDTO, newItem: GitHubRepositoryDTO): Boolean {
                return oldItem == newItem
            }
        }
    }
}
