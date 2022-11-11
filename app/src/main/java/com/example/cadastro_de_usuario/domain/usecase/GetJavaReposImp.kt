package com.example.cadastro_de_usuario.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.domain.converter.RepoConverter
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val LANGUAGE_REPOS = "language:java"

internal class GetJavaReposImp(
    private val repository: GitHubRepository,
    private val converter: RepoConverter
) : GetJavaReposUseCase {

    override operator fun invoke(): Flow<PagingData<GitHubListVO>> =
        repository.getRepos(LANGUAGE_REPOS)
            .map { pageData ->
                pageData.map(converter::convert)
            }
}