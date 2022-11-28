package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.dbsqlite.DataBaseSQLite
import com.example.cadastro_de_usuario.databinding.FragmentRegisterBinding
import com.example.cadastro_de_usuario.domain.vo.UserDataVO
import kotlinx.android.synthetic.main.fragment_register.input_layout_name
import kotlinx.android.synthetic.main.fragment_register.input_layout_email
import kotlinx.android.synthetic.main.fragment_register.input_layout_senha
import kotlinx.android.synthetic.main.fragment_register.edit_cadastro_name
import kotlinx.android.synthetic.main.fragment_register.edit_cadastro_email
import kotlinx.android.synthetic.main.fragment_register.edit_cadastro_senha
import java.security.MessageDigest

internal class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding by lazy { FragmentRegisterBinding.bind(requireView()) }
    private val dataBaseSQLite by lazy { DataBaseSQLite(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupButtonSave()
        dataFocusListener()
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun setupButtonSave() {
        binding.buttonCadastroSave.setOnClickListener {
            input_layout_name.helperText = validName()
            input_layout_email.helperText = validEmail()
            input_layout_senha.helperText = validPassword()

            val validNome = binding.inputLayoutName.helperText == null
            val validEmail = binding.inputLayoutEmail.helperText == null
            val validSenha = binding.inputLayoutSenha.helperText == null

            if (validEmail && validSenha && validNome) {
                ChoiceRoute()
            } else {
                setupDialogAlert()
            }
        }
    }

    private fun ChoiceRoute() {
        val checkedRadioGroup = binding.radioGroupCadastro.checkedRadioButtonId
        saveValuesDataBase(checkedRadioGroup)
        if (checkedRadioGroup == R.id.radio_cadastro_admin) {
            findNavController().navigate(R.id.action_registerFragment_to_userManagementFragment)
        } else {
            findNavController().navigate(R.id.action_registerFragment_to_listFragment)
        }
    }

    private fun setupDialogAlert() {
        var message = ""
        if (binding.inputLayoutName.helperText != null)
            message+= "\n" + binding.inputLayoutName.helperText
        if (binding.inputLayoutEmail.helperText != null)
            message+= "\n\n" + binding.inputLayoutEmail.helperText
        if (binding.inputLayoutSenha.helperText != null)
            message+= "\n\n" + binding.inputLayoutSenha.helperText

        AlertDialog.Builder(requireContext())
            .setTitle("Cadastro Inválido")
            .setMessage(message)
            .setPositiveButton("ok") {_,_ -> }
            .show()
    }

    private fun saveValuesDataBase(checkedRadioGroup: Int) {
        val cont = dataBaseSQLite.getUserList().size
        dataBaseSQLite.addUser(
            userDataVO = UserDataVO(
                id = cont + 1,
                name = edit_cadastro_name.text.toString(),
                email = edit_cadastro_email.text.toString(),
                password = setupHash(edit_cadastro_senha.text.toString().toByteArray()),
                type = checkedRadioGroup
            )
        )
    }

    private fun setupHash(inByte: ByteArray): String {
        val digestedBytes = MessageDigest.getInstance("SHA-256").digest(inByte)
        return with(StringBuilder()) {
            digestedBytes.forEach { b -> append(String.format("%02X", b)) }
            toString().lowercase()
        }
    }

    private fun dataFocusListener() {
        binding.editCadastroName.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                input_layout_name.helperText = validName()
            }
        }
        binding.editCadastroEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                input_layout_email.helperText = validEmail()
            }
        }
        binding.editCadastroSenha.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                input_layout_senha.helperText = validPassword()
            }
        }
    }

    private fun validName(): String? {
        val nomeText = edit_cadastro_name.text.toString()
        if (nomeText.length <= 10){
            return "Nome inválido"
        }
        return null
    }

    private fun validEmail(): String? {
        val emailText = edit_cadastro_email.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
            return "e-mail inválido"
        }
        return null
    }

    private fun validPassword(): String {
        val senhaText = edit_cadastro_senha.text.toString()
        if (senhaText.length <= 8) {
            return "Deve conter mais de 8 caracteres"
        }else if (!senhaText.matches(".*[A-Z].*".toRegex())) {
            return "Deve conter no minimo uma letra maiúscula"
        }else if (!senhaText.matches(".*[1-9].*".toRegex())) {
            return "Deve conter no minimo um numero"
        }
        return ""
    }
}
