package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.datastore.preferences.protobuf.ByteString
import androidx.navigation.fragment.findNavController
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.databinding.FragmentLoginBinding
import okio.HashingSource.Companion.sha256
import java.security.MessageDigest

internal class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by lazy {FragmentLoginBinding.bind(requireView())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupButton()
        getPassword()
    }

    private fun setupButton() = with(binding){

        buttonLogar.setOnClickListener {
            TODO()
        }

        textRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun getPassword() {
        val password = binding.inputPassword.toString()
        val encryptedPassword = getHash(password.toByteArray())
        Log.e(encryptedPassword, "testando Cryptography")
    }

    private fun getHash(inByte: ByteArray): String {
        val digestedBytes = MessageDigest.getInstance("SHA-256").digest(inByte)
        return with(StringBuilder()) {
            digestedBytes.forEach { b -> append(String.format("%02X", b)) }
            toString().lowercase()
        }
    }


}