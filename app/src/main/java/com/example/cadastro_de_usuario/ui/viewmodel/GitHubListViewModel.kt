package com.example.cadastro_de_usuario.ui.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import com.example.cadastro_de_usuario.data.retrofit.GitHubApi
import com.example.cadastro_de_usuario.paging.GitHubPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class GitHubListViewModel @Inject constructor(private val gitHubApi: GitHubApi) : ViewModel() {

    fun fetchInformation(): Flow<PagingData<GitHubRepositoryDTO>> {
        return Pager(
            config = PagingConfig(pageSize = 350),
            pagingSourceFactory = { GitHubPagingSource(gitHubApi) }
        ).flow.cachedIn(viewModelScope)
    }
}
