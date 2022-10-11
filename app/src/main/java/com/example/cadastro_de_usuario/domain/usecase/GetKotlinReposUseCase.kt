package com.example.cadastro_de_usuario.domain.usecase

import androidx.paging.PagingData
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import kotlinx.coroutines.flow.Flow

internal interface GetKotlinReposUseCase {

    operator fun invoke(): Flow<PagingData<GitHubListVO>>
}