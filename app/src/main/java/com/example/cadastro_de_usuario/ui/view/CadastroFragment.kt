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
import com.example.cadastro_de_usuario.databinding.FragmentCadastroBinding
import kotlinx.android.synthetic.main.fragment_cadastro.*
import kotlinx.coroutines.launch

class CadastroFragment : Fragment() {

    private lateinit var userManager: UserManager
    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        userManager = UserManager(requireContext())

        setupToolbar()
        setupButtonSave()

        return binding.root
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)
        }
    }

    private fun setupButtonSave() {
        binding.buttonCadastroSave.setOnClickListener {
            val checkedRadioGroup = binding.radioGroupCadastro.checkedRadioButtonId
            lifecycleScope.launch {
                userManager.saveValues(

                    edit_cadastro_name.text.toString(),
                    edit_cadastro_email.text.toString(),
                    edit_cadastro_password.text.toString().toInt(),
                    checkedRadioGroup
                )
            }
            if (checkedRadioGroup == R.id.radio_cadastro_admin) {
                findNavController().navigate(R.id.action_cadastroFragment_to_gestaoFragment)
            }else {
                findNavController().navigate(R.id.action_cadastroFragment_to_listaFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}