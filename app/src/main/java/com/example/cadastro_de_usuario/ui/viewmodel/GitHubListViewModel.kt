package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.*
import com.example.cadastro_de_usuario.RetrofitServices
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.domain.converter.GitHubListConverter
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class GitHubListViewModel(
    private val repository: GitHubRepository,
    private val converter: GitHubListConverter,
): ViewModel() {

    private val _listRepository = MutableLiveData<List<GitHubListVO>>()
    val listRepository: LiveData<List<GitHubListVO>> = _listRepository

    fun fetchInformation() {
        viewModelScope.launch {
            val response = repository.getListRepository()
            val listVO = converter.converter(response.items)
            _listRepository.postValue(listVO)
        }
    }
}

