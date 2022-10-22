package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.databinding.FragmentPullRequestListBinding
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO
import com.example.cadastro_de_usuario.ui.adapter.ListPullsAdapter
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.VERTICAL


internal class PullRequestListFragment : Fragment(R.layout.fragment_pull_request_list) {

    private val binding by lazy { FragmentPullRequestListBinding.bind(requireView())}
    private val itemAdapter by lazy { ListPullsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        recyclerView.adapter = itemAdapter
        recyclerView .addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        itemAdapter.submitList(fakeList)
    }

    private val fakeList = listOf(
        ListPullsVO(title = "Item 1"),
        ListPullsVO(title = "Item 2"),
        ListPullsVO(title = "Item 3"),
        ListPullsVO(title = "Item 4"),
        ListPullsVO(title = "Item 5"),
        ListPullsVO(title = "Item 6"),
    )
}