package com.example.cadastro_de_usuario.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.cadastro_de_usuario.data.retrofit.GitHubApi
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import com.example.cadastro_de_usuario.paging.GitHubPagingSource
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 10
private const val PAGE_PLACE_HOLDERS = false

internal class GitHubRepositoryImp(private val service: GitHubApi): GitHubRepository {

    override fun getRepos(language: String): Flow<PagingData<GitHubRepositoryDTO>> =
        Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
                enablePlaceholders = PAGE_PLACE_HOLDERS
            ),
            pagingSourceFactory = { GitHubPagingSource(service, language) }
        ).flow
}