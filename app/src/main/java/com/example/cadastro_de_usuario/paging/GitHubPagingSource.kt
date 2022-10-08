package com.example.cadastro_de_usuario.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cadastro_de_usuario.data.retrofit.GitHubApi
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryListResponseDTO

class GitHubPagingSource(
    private val gitHubApi: GitHubApi
): PagingSource<Int, GitHubRepositoryDTO>() {

    companion object {
        private const val TOTAL_PAGE = 350
        private const val FIRST_PAGE = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepositoryDTO> {
        return try {
            val position = params.key ?: FIRST_PAGE
            val response = gitHubApi.getRepositories(position)

            return LoadResult.Page(
                data = response.items,
                prevKey = if (position == FIRST_PAGE) null else position -1,
                nextKey = if (position == TOTAL_PAGE) null else position +1,
            )
        }
        catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitHubRepositoryDTO>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
