package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.commons.*
import com.example.cadastro_de_usuario.commons.ErrorAction
import com.example.cadastro_de_usuario.commons.LoadAction
import com.example.cadastro_de_usuario.commons.SuccessAction
import com.example.cadastro_de_usuario.commons.isLoading
import com.example.cadastro_de_usuario.databinding.FragmentListRepositoryBinding
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO
import com.example.cadastro_de_usuario.ui.adapter.ListRepositoriesAdapter
import com.example.cadastro_de_usuario.ui.viewmodel.GitHubListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class GitHubListFragment : Fragment(R.layout.fragment_list_repository) {

    private val binding by lazy { FragmentListRepositoryBinding.bind(requireView()) }
    private val viewModel: GitHubListViewModel by viewModel()
    private val repositoriesAdapter by lazy { ListRepositoriesAdapter{ onClickItem() } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupObserver()
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_gestaoFragment)
        }
    }

    private fun setupObserver() {
        viewModel.repositories.observe(viewLifecycleOwner ) {
            when (it) {
                is ErrorAction -> onError()
                is LoadAction -> onLoading()
                is SuccessAction -> onSuccess(it.data)
            }
        }
        viewModel.fetchRepositories()
    }

    private fun getLoadStateListener(): (CombinedLoadStates) -> Unit = {
        when {
            it.isLoading() -> onLoading()
            it.isError() -> onError()
            else -> hideLoading()
        }
    }

    private fun onSuccess(data: PagingData<GitHubListVO>) = with(repositoriesAdapter) {
        hideLoading()
        initRecyclerView()
        addLoadStateListener(getLoadStateListener())
        submitData(viewLifecycleOwner.lifecycle, data)
    }

    private fun initRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        adapter = repositoriesAdapter
    }

    private fun onClickItem() {
        findNavController().navigate(R.id.action_listFragment_to_pullRequestListFragment)
    }

    private fun hideLoading() {
        binding.reposRefreshLayout.isRefreshing = false
    }

    private fun onLoading() {
        binding.reposRefreshLayout.isRefreshing = true
        binding.reposRefreshLayout.isVisible = true
    }

    private fun onError() {
        Toast.makeText(requireContext(),"ERROR", Toast.LENGTH_LONG).show()
    }
}
