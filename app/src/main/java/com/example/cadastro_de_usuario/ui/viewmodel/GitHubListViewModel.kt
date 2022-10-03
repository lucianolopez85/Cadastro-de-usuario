package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.*
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.domain.converter.GitHubListConverter
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import kotlinx.coroutines.launch

class GitHubListViewModel(
    private val repository: GitHubRepository,
    private val converter: GitHubListConverter
): ViewModel() {

    private val _listRepository = MutableLiveData<List<GitHubListVO>>()
    val listRepository: LiveData<List<GitHubListVO>> = _listRepository

    fun fetchInformation(page: Int) {
        viewModelScope.launch {
            val response = repository.getListRepository(page)
            val listVO = converter.converter(response.items)
            _listRepository.postValue(listVO)
        }
    }
}
