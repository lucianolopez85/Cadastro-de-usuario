package com.example.cadastro_de_usuario.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.databinding.ItemRepositoryBinding
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import com.squareup.picasso.Picasso

class GitHubListAdapter(
    private val items: List<GitHubListVO> = ArrayList()
) : RecyclerView.Adapter<GitHubListAdapter.AdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder =
        AdapterViewHolder(
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int  = items.size

    inner class AdapterViewHolder(private val binding: ItemRepositoryBinding): RecyclerView.ViewHolder(binding.root) {

        private val picasso: Picasso by lazy { Picasso.get() }

        fun bind(itemView: GitHubListVO) {
            binding.root.setOnClickListener {
                Log.e("itemViewID", itemView.id)
            }
            binding.textRepositoryName.text = itemView.name
            binding.textDescription.text = itemView.description
            binding.textStar.text = itemView.star
            binding.textFork.text = itemView.fork
            setImageView(binding.avatar, itemView.avatar)
        }

        fun setImageView(view: ImageView, avatar: String) {
            picasso
                .load(avatar)
                .fit()
                .into(view)
        }

    }
}
