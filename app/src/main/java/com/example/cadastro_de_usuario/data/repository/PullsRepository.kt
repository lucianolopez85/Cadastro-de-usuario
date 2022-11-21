package com.example.cadastro_de_usuario.data.repository

import com.example.cadastro_de_usuario.data.retrofit.GitHubApi

internal class PullsRepository(private val service: GitHubApi) {

    suspend fun getListRepository(owner: String, repo: String) =
        service.getSearchListPull(owner, repo)
}