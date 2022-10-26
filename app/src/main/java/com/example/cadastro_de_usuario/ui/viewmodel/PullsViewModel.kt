package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cadastro_de_usuario.data.repository.PullsRepository
import com.example.cadastro_de_usuario.domain.converter.RepoConverter
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO
import kotlinx.coroutines.launch

internal class PullsViewModel(
    private val repository: PullsRepository,
    private val converter: RepoConverter
): ViewModel() {

    private val _listRepository = MutableLiveData<List<ListPullsVO>>()
    val listRepository: LiveData<List<ListPullsVO>> = _listRepository

    fun fetchInformation() {
        viewModelScope.launch {
            val response = repository.getListRepository()
            val listVO = converter.converter(response.pulls)
            _listRepository.postValue(listVO)
        }
    }

}