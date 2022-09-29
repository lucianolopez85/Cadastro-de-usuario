package com.example.cadastro_de_usuario.data

import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryListResponseDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ApiService {

    @GET("search/repositories?q=language:Java&sort=stars&/page=1")
    fun getRepositories() : Flow<GitHubRepositoryListResponseDTO>
}