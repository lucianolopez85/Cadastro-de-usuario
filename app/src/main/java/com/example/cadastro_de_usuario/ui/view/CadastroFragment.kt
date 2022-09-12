package com.example.cadastro_de_usuario.ui.view

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.DataBaseSQLite
import com.example.cadastro_de_usuario.data.User
import com.example.cadastro_de_usuario.databinding.FragmentCadastroBinding
import kotlinx.android.synthetic.main.fragment_cadastro.*

class CadastroFragment : Fragment() {

    private lateinit var dataBaseSQLite: DataBaseSQLite
    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)
        dataBaseSQLite = DataBaseSQLite(requireContext())

        setupToolbar()
        setupButtonSave()
        dataFocusListener()

        return binding.root
    }

    private fun setupToolbar() {
        binding.iconBack.setOnClickListener {
            findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)
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
            findNavController().navigate(R.id.action_cadastroFragment_to_gestaoFragment)
        } else {
            findNavController().navigate(R.id.action_cadastroFragment_to_listaFragment)
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
        val cont = dataBaseSQLite.getListUser().size
        dataBaseSQLite.addUser(
            user = User(
                id = cont + 1,
                name = edit_cadastro_name.text.toString(),
                email = edit_cadastro_email.text.toString(),
                password = edit_cadastro_senha.text.toString(),
                type = checkedRadioGroup
            )
        )
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
