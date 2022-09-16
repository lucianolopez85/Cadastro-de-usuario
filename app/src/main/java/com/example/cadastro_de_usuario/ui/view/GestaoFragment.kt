package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.DataBaseSQLite
import com.example.cadastro_de_usuario.data.User
import com.example.cadastro_de_usuario.databinding.FragmentGestaoBinding
import com.example.cadastro_de_usuario.ui.adapter.ListAdapter
import kotlinx.android.synthetic.main.fragment_gestao.view.*

class GestaoFragment : Fragment(R.layout.fragment_gestao) {

    private val dataBaseSQLite: DataBaseSQLite by lazy { DataBaseSQLite(requireContext()) }
    private val binding: FragmentGestaoBinding by lazy { FragmentGestaoBinding.bind(requireView()) }

    override fun onViewCreated(view: View, savedInstancesState: Bundle?) {

        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_gestaoFragment_to_cadastroFragment)
        }
        binding.buttonLista.setOnClickListener {
            findNavController().navigate(R.id.action_gestaoFragment_to_listaFragment)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.recyclerViewListUser) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = ListAdapter(dataBaseSQLite.getListUser())
    }
}
