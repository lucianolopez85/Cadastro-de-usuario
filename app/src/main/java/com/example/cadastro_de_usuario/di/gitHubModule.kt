package com.example.cadastro_de_usuario.di

import com.example.cadastro_de_usuario.data.dbsqlite.DataBaseSQLite
import com.example.cadastro_de_usuario.data.retrofit.GitHubApi
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.data.repository.GitHubRepositoryImp
import com.example.cadastro_de_usuario.data.repository.PullsRepository
import com.example.cadastro_de_usuario.domain.converter.PullsRepoConverter
import com.example.cadastro_de_usuario.domain.converter.RepoConverter
import com.example.cadastro_de_usuario.domain.usecase.GetJavaReposUseCase
import com.example.cadastro_de_usuario.domain.usecase.GetJavaReposImp
import com.example.cadastro_de_usuario.ui.viewmodel.GitHubListViewModel
import com.example.cadastro_de_usuario.ui.viewmodel.PullsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val gitHubModule = module {

    factory<GitHubRepository> { GitHubRepositoryImp(get()) }

    factory<GetJavaReposUseCase> { GetJavaReposImp(get(), get()) }

    factory { RepoConverter() }

    viewModel { GitHubListViewModel(get()) }

    factory<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }

    factory { PullsRepository(get()) }

    factory { PullsRepoConverter() }

    viewModel { PullsViewModel(get(),get()) }

    factory { DataBaseSQLite(get()) }
}