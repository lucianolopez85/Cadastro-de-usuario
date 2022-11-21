package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cadastro_de_usuario.data.repository.PullsRepository
import com.example.cadastro_de_usuario.domain.converter.PullsRepoConverter
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO
import kotlinx.coroutines.launch

internal class PullsViewModel(
    private val repository: PullsRepository,
    private val converter: PullsRepoConverter
): ViewModel() {

    private val _listRepository = MutableLiveData<List<ListPullsVO>>()
    val listRepository: LiveData<List<ListPullsVO>> = _listRepository

    fun fetchInformation(owner: String, repo: String) {
        viewModelScope.launch {
            val response = repository.getListRepository(owner, repo)
            val list = converter.convert(response)
            _listRepository.postValue(list)
        }
    }
}