package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.domain.converter.ListRepositoryConverter
import com.example.cadastro_de_usuario.domain.vo.GitHubRepositoryVO
import kotlinx.coroutines.flow.map

class ListRepositoryViewModel(
    private val repository: GitHubRepository,
    private val converter: ListRepositoryConverter
) {
    private var listVO: List<GitHubRepositoryVO> = arrayListOf()

    private val _listRepository = MutableLiveData<List<GitHubRepositoryVO>>()
    val listRepository: LiveData<List<GitHubRepositoryVO>> = _listRepository.switchMap {
        repository.getListRepository()
            .map { dto ->
                converter.converter(dto.Itens)
            }.asLiveData()
    }

    fun fetchInformation() {
        _listRepository.postValue(listVO)
    }
}

