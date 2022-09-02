package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.UserManager
import com.example.cadastro_de_usuario.databinding.FragmentGestaoBinding
import kotlinx.android.synthetic.main.fragment_gestao.*
import kotlinx.coroutines.launch

class GestaoFragment : Fragment() {

    private lateinit var userManager: UserManager
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
            getUserProfile()
//            findNavController().navigate(R.id.action_gestaoFragment_to_listaFragment)
        }

        return binding.root
    }

    private fun getUserProfile() {
        lifecycleScope.launch {
            userManager.readDataUser().collect{
                text_nome.text = it.name
                text_email.text = it.email
                text_senha.text = it.senha.toString()
                text_tipo.text = it.tipo.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
