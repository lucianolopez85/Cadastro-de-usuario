package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.View.VISIBLE
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.DataBaseSQLite
import com.example.cadastro_de_usuario.data.UserDataVO
import com.example.cadastro_de_usuario.databinding.FragmentGestaoBinding
import com.example.cadastro_de_usuario.ui.adapter.ListAdapter
import com.example.cadastro_de_usuario.ui.viewmodel.GestaoViewModel

class GestaoFragment : Fragment(R.layout.fragment_gestao) {

    private val binding: FragmentGestaoBinding by lazy { FragmentGestaoBinding.bind(requireView()) }
    private val viewModel: GestaoViewModel by lazy { GestaoViewModel(DataBaseSQLite(requireContext()))}

    override fun onViewCreated(view: View, savedInstancesState: Bundle?) {

        setupToolbar()
        setupObserver()
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_gestaoFragment_to_cadastroFragment)
        }
        binding.buttonLista.setOnClickListener {
            findNavController().navigate(R.id.action_gestaoFragment_to_listaFragment)
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
