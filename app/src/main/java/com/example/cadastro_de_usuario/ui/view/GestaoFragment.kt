package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.User
import com.example.cadastro_de_usuario.data.UserManager
import com.example.cadastro_de_usuario.databinding.FragmentGestaoBinding
import com.example.cadastro_de_usuario.ui.adapter.ListAdapter
import kotlinx.coroutines.launch

class GestaoFragment : Fragment() {

    private lateinit var userManager: UserManager
    private lateinit var userList : ArrayList<User>
    private var _binding: FragmentGestaoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGestaoBinding.inflate(inflater, container, false)
        userManager = UserManager(requireContext())

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
        lifecycleScope.launch {
            userManager.readDataUser().collect{
                userList = ArrayList()
                userList.add(
                    User(name = it.name, email = it.email, senha = it.senha, tipo = it.tipo)
                )
            }
        }
        adapter = ListAdapter(userList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
