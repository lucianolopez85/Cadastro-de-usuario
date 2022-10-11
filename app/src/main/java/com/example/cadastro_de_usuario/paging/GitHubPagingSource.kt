package com.example.cadastro_de_usuario.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import com.example.cadastro_de_usuario.data.retrofit.GitHubApi
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE_INDEX = 1

internal class GitHubPagingSource(
    private val service: GitHubApi,
    private val language: String
) : PagingSource<Int, GitHubRepositoryDTO>() {

    override fun getRefreshKey(state: PagingState<Int, GitHubRepositoryDTO>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepositoryDTO> {
        val position = params.getCurrentPosition()

        return try {
            val response = service.getSearchRepositories(page = position, language = language)
            val repos = response.items.orEmpty()
            val nextKey = takeIf { position < response.totalCount }?.let {
                position.inc()
            }

            LoadResult.Page(
                data = repos,
                prevKey = params.getPrevKey(),
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    private fun LoadParams<Int>.getPrevKey() =
        key?.takeIf { it > START_PAGE_INDEX }?.dec()

    private fun LoadParams<Int>.getCurrentPosition() =
        key ?: START_PAGE_INDEX
}