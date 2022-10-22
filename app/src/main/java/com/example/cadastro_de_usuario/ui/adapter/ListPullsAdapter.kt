package com.example.cadastro_de_usuario.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.databinding.ItemPullRequestBinding
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO

internal class ListPullsAdapter : ListAdapter<ListPullsVO, ListPullsAdapter.ViewHolder>(DiffUtil) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemPullRequestBinding.bind(view)

        fun bind(item: ListPullsVO) {
            binding.textView.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
        }
    }
}

private val DiffUtil = object : DiffUtil.ItemCallback<ListPullsVO>() {
    override fun areItemsTheSame(oldItem: ListPullsVO, newItem: ListPullsVO) =
        oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: ListPullsVO, newItem: ListPullsVO) =
        oldItem == newItem

}