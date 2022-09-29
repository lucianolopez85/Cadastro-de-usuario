package com.example.cadastro_de_usuario.data

import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryListResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("search/repositories?q=language:Java&sort=stars&/")
    suspend fun getRepositories(
        @Query("page") page : Int
    ) : GitHubRepositoryListResponseDTO

}