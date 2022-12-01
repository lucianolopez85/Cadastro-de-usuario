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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import com.example.cadastro_de_usuario.ui.viewmodel.PullsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class PullRequestListFragment : Fragment(R.layout.fragment_pull_request_list) {

    private val binding by lazy { FragmentPullRequestListBinding.bind(requireView()) }
    private val itemAdapter by lazy { ListPullsAdapter() }
    private val viewModel: PullsViewModel by viewModel()
    private val repository by lazy { arguments?.getSerializable("REPOSITORY_DATA") as GitHubListVO }
    private val userType by lazy { arguments?.let { it.getInt("USER_TYPE_DATA") } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("typeData", userType!!)
            findNavController().navigate(R.id.action_pullRequestListFragment_to_listFragment, bundle)
        }
    }

    private fun setupObserver() = with(viewModel) {
        listRepository.observe(viewLifecycleOwner, ::onSuccess)
        fetchInformation(repository.login, repository.nameRepository)
    }

    private fun onSuccess(list: List<ListPullsVO>) = with(binding.recyclerView) {
        initRecyclerView(list)
        visibility = View.VISIBLE
    }

    private fun initRecyclerView(list: List<ListPullsVO>) = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        setHasFixedSize(true)
        adapter = itemAdapter
        itemAdapter.submitList(list)
    }
}
