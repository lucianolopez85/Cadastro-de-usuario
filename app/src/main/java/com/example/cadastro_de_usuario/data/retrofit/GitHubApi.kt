package com.example.cadastro_de_usuario.data.retrofit

import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryListResponseDTO
import com.example.cadastro_de_usuario.data.dto.PullsListResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val SORT = "stars"

internal interface GitHubApi {

    @GET("search/repositories")
    suspend fun getSearchRepositories(
        @Query("sort") sort: String = SORT,
        @Query("page") page: Int,
        @Query("q") language: String
    ): GitHubRepositoryListResponseDTO

    @GET("repos/{owner}/{repo}/pulls")
    suspend fun getSearchListPull(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
    ): PullsListResponseDTO
}
