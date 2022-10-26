package com.example.cadastro_de_usuario.data.repository

import com.example.cadastro_de_usuario.data.retrofit.GitHubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class PullsRepository(private val service: GitHubApi) {

    suspend fun getListRepository() = withContext(Dispatchers.IO) {
        service.getSearchListPull("spring-projects","spring-boot" )
    }

}