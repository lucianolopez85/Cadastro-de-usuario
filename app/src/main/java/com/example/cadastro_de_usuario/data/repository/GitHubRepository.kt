package com.example.cadastro_de_usuario.data.repository

import androidx.paging.PagingData
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import kotlinx.coroutines.flow.Flow

internal interface GitHubRepository {

    fun getRepos(language: String): Flow<PagingData<GitHubRepositoryDTO>>
}