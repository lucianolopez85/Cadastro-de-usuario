package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.*
import com.example.cadastro_de_usuario.data.DataBaseSQLite
import com.example.cadastro_de_usuario.data.UserDataVO

internal class GestaoViewModel(
    private val dataBaseSQLite: DataBaseSQLite
) : ViewModel() {

    private val _userData = MutableLiveData<List<UserDataVO>>()
    val userData: LiveData<List<UserDataVO>> = _userData

    fun fetchInformation() {
        _userData.postValue(dataBaseSQLite.getUserList())
    }
}
