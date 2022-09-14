package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.DataBaseSQLite
import com.example.cadastro_de_usuario.databinding.FragmentGestaoBinding
import com.example.cadastro_de_usuario.ui.adapter.ListAdapter

class GestaoFragment : Fragment() {

    private lateinit var dataBaseSQLite: DataBaseSQLite
    private lateinit var listAdapter: ListAdapter
    private var _binding: FragmentGestaoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGestaoBinding.inflate(inflater, container, false)
        dataBaseSQLite = DataBaseSQLite(requireContext())
        listAdapter = ListAdapter(dataBaseSQLite.getListUser())

        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_gestaoFragment_to_cadastroFragment)
        }
        binding.buttonLista.setOnClickListener {
            findNavController().navigate(R.id.action_gestaoFragment_to_listaFragment)
        }
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() = with(binding.recyclerViewListUser) {
        layoutManager = LinearLayoutManager(requireContext())
        setHasFixedSize(true)
        adapter = listAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
