package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.dbsqlite.DataBaseSQLite
import com.example.cadastro_de_usuario.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.input_password
import kotlinx.android.synthetic.main.fragment_login.input_login
import java.security.MessageDigest

private const val ADMIN = 2131231110

internal class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by lazy { FragmentLoginBinding.bind(requireView()) }
    private val dataBaseSQLite by lazy { DataBaseSQLite(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButton()
        setupPassword()
    }

    private fun setupButton() = with(binding) {
        textClick.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun setupPassword() {
        var login = ""
        var password = ""
        val loginListSave = dataBaseSQLite.getUserList().map { it.email }
        val passwordListSave = dataBaseSQLite.getUserList().map { it.password }
        binding.buttonLogar.setOnClickListener {
            login = input_login.text.toString()
            password = getHash(input_password.text.toString().toByteArray())
            if (login in loginListSave && password in passwordListSave) {
                ChoiceRoute(login)
            }else{
                binding.textAlert.isVisible = true
            }
        }
    }

    private fun ChoiceRoute(login: String) {
        val userData = dataBaseSQLite.getUser(login)

        if (userData.type == ADMIN) {
            findNavController().navigate(R.id.action_loginFragment_to_UserManagementFragment)
        } else {
            val bundle = Bundle()
            bundle.putInt("typeData", userData.type)
            findNavController().navigate(R.id.action_loginFragment_to_listFragment, bundle)
        }
    }

    private fun getHash(inByte: ByteArray): String {
        val digestedBytes = MessageDigest.getInstance("SHA-256").digest(inByte)
        return with(StringBuilder()) {
            digestedBytes.forEach { b -> append(String.format("%02X", b)) }
            toString().lowercase()
        }
    }
}
