package com.example.cadastro_de_usuario.data.repository

import com.example.cadastro_de_usuario.RetrofitServices
import com.example.cadastro_de_usuario.data.GitHubApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitHubRepository {

    private val api: GitHubApi = RetrofitServices.gitHubApi

    suspend fun getListRepository() = withContext(Dispatchers.IO) { api.getRepositories(1) }
}