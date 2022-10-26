package com.example.cadastro_de_usuario.di

import com.example.cadastro_de_usuario.data.retrofit.GitHubApi
import com.example.cadastro_de_usuario.data.repository.GitHubRepository
import com.example.cadastro_de_usuario.data.repository.GitHubRepositoryImp
import com.example.cadastro_de_usuario.domain.converter.RepoConverter
import com.example.cadastro_de_usuario.domain.usecase.GetKotlinReposUseCase
import com.example.cadastro_de_usuario.domain.usecase.GetKotlinReposImp
import com.example.cadastro_de_usuario.ui.viewmodel.GitHubListViewModel
import com.example.cadastro_de_usuario.ui.viewmodel.PullsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

internal val gitHubModule = module {
    factory<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }

    factory<GitHubRepository> { GitHubRepositoryImp(get()) }

    factory { RepoConverter() }

    factory<GetKotlinReposUseCase> { GetKotlinReposImp(get(), get()) }

    viewModel { GitHubListViewModel(get()) }

    viewModel { PullsViewModel(get(), get()) }
}