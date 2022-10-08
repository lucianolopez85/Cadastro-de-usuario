package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.databinding.FragmentListRepositoryBinding
import com.example.cadastro_de_usuario.ui.adapter.PagingAdapter
import com.example.cadastro_de_usuario.ui.viewmodel.GitHubListViewModel
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class GitHubListFragment : Fragment(R.layout.fragment_list_repository) {

    private val binding: FragmentListRepositoryBinding by lazy { FragmentListRepositoryBinding.bind(requireView()) }
    lateinit var viewModel: GitHubListViewModel
    lateinit var pagingAdapter: PagingAdapter

    override fun onViewCreated(view: View, savedInstancesState: Bundle?) {
        setupToolbar()
        initRecyclerView()
        initViewModel()
    }


    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_gestaoFragment)
        }
    }

    private fun initRecyclerView() = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        pagingAdapter = PagingAdapter()
        adapter = pagingAdapter
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this).get(GitHubListViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.fetchInformation().collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

}
