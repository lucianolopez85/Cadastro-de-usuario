package com.example.cadastro_de_usuario.data

import com.example.cadastro_de_usuario.data.dto.GitHubResponseDTO
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow

interface GitHubRepositoryApi {

    @GET("https://api.github.com/repos")
    fun getListRepositoryApi() : Flow<List<GitHubResponseDTO>>
}