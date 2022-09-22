package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.VISIBLE
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.dbsqlite.DataBaseSQLite
import com.example.cadastro_de_usuario.domain.vo.UserDataVO
import com.example.cadastro_de_usuario.databinding.FragmentUserManagementBinding
import com.example.cadastro_de_usuario.ui.adapter.ListAdapter
import com.example.cadastro_de_usuario.ui.viewmodel.UserManagementViewModel

class UserManagementFragment : Fragment(R.layout.fragment_user_management) {

    private val binding: FragmentUserManagementBinding by lazy { FragmentUserManagementBinding.bind(requireView()) }
    private val viewModel: UserManagementViewModel by lazy { UserManagementViewModel(DataBaseSQLite(requireContext()))}

    override fun onViewCreated(view: View, savedInstancesState: Bundle?) {

        setupToolbar()
        setupObserver()
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_userManagementFragment_to_registerFragment)
        }
        binding.buttonLista.setOnClickListener {
            findNavController().navigate(R.id.action_userManagementFragment_to_listFragment)
        }
    }

    private fun setupObserver() {
        viewModel.userData.observe(viewLifecycleOwner, ::onSuccess)
        viewModel.fetchInformation()
    }

    private fun onSuccess(list: List<UserDataVO>) = with(binding.recyclerView) {
        initRecyclerView(list)
        visibility = VISIBLE
    }

    private fun initRecyclerView(list: List<UserDataVO>) = with(binding.recyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        adapter = ListAdapter(list)
    }
}
