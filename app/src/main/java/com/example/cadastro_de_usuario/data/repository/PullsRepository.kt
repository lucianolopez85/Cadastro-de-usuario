package com.example.cadastro_de_usuario.data.repository

import com.example.cadastro_de_usuario.data.retrofit.GitHubApi

internal class PullsRepository(private val service: GitHubApi) {

    suspend fun getListRepository() =
        service.getSearchListPull("spring-projects","spring-boot" )

}