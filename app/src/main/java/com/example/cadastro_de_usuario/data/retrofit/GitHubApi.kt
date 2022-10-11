package com.example.cadastro_de_usuario.data.retrofit

import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryListResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

private const val ORDER = "desc"
private const val SORT = "stars"

internal interface GitHubApi {

    @GET("search/repositories")
    suspend fun getSearchRepositories(
        @Query("sort") sort: String = SORT,
        @Query("order") order: String = ORDER,
        @Query("page") page: Int,
        @Query("q") language: String
    ): GitHubRepositoryListResponseDTO
}
