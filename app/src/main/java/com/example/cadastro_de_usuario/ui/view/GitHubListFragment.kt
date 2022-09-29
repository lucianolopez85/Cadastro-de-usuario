package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.RetrofitServices
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.databinding.FragmentListRepositoryBinding
import com.example.cadastro_de_usuario.domain.converter.GitHubListConverter
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import com.example.cadastro_de_usuario.ui.adapter.GitHubListAdapter
import com.example.cadastro_de_usuario.ui.viewmodel.GitHubListViewModel
import kotlinx.coroutines.launch

class GitHubListFragment : Fragment(R.layout.fragment_list_repository) {

    private val binding: FragmentListRepositoryBinding by lazy { FragmentListRepositoryBinding.bind(requireView()) }
    private val viewModel: GitHubListViewModel by lazy { GitHubListViewModel(GitHubRepository(), GitHubListConverter()) }

    override fun onViewCreated(view: View, savedInstancesState: Bundle?) {
        setupToolbar()
        setupObserver()
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_gestaoFragment)
        }
    }

    private fun setupObserver() {
        viewModel.listRepository.observe(viewLifecycleOwner, ::onSuccess)
        viewModel.fetchInformation()
    }

    private fun onSuccess(list: List<GitHubListVO>) = with(binding.recyclerView) {
        initRecyclerView(list)
        visibility = View.VISIBLE
    }

    private fun initRecyclerView(list: List<GitHubListVO>) = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        adapter = GitHubListAdapter(list)
    }
}
