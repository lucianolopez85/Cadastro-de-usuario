package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.cadastro_de_usuario.commons.UiAction
import com.example.cadastro_de_usuario.commons.asLiveData
import com.example.cadastro_de_usuario.domain.usecase.GetKotlinReposUseCase
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import kotlinx.coroutines.flow.distinctUntilChanged

internal class GitHubListViewModel (private val useCase: GetKotlinReposUseCase) : ViewModel() {

    private val _repositories = MutableLiveData<Unit>()
    val repositories: LiveData<UiAction<PagingData<GitHubListVO>>> = _repositories.switchMap {
        useCase()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .asLiveData()
    }

    fun fetchRepositories() {
        _repositories.value = Unit
    }
}
