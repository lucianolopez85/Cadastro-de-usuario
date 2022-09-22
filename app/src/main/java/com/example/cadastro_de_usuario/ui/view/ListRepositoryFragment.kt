package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.dbsqlite.DataBaseSQLite
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.databinding.ActivityMainBinding.bind
import com.example.cadastro_de_usuario.databinding.FragmentListRepositoryBinding
import com.example.cadastro_de_usuario.databinding.ItemRepositoryBinding.bind
import com.example.cadastro_de_usuario.domain.converter.ListRepositoryConverter
import com.example.cadastro_de_usuario.domain.vo.GitHubRepositoryVO
import com.example.cadastro_de_usuario.domain.vo.UserDataVO
import com.example.cadastro_de_usuario.ui.adapter.GitHubRepositoryAdapter
import com.example.cadastro_de_usuario.ui.adapter.ListAdapter
import com.example.cadastro_de_usuario.ui.viewmodel.ListRepositoryViewModel
import com.example.cadastro_de_usuario.ui.viewmodel.UserManagementViewModel
import kotlinx.coroutines.flow.flow
class ListRepositoryFragment : Fragment(R.layout.fragment_list_repository) {

    private val binding: FragmentListRepositoryBinding by lazy { FragmentListRepositoryBinding.bind(requireView()) }
    private val viewModel: ListRepositoryViewModel by lazy { ListRepositoryViewModel(GitHubRepository(), ListRepositoryConverter()) }

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

    private fun onSuccess(list: List<GitHubRepositoryVO>) = with(binding.recyclerView) {
        initRecyclerView(list)
        visibility = View.VISIBLE
    }

    private fun initRecyclerView(list: List<GitHubRepositoryVO>) = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        adapter = GitHubRepositoryAdapter(list)
    }
}
