package com.example.cadastro_de_usuario

import com.example.cadastro_de_usuario.domain.vo.GitHubRepositoryVO
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NetworkUtils {

    @GET("search/repositories?q=language:Java&sort=stars&/page=1")
    fun getAllRecipes(): Call<List<GitHubRepositoryVO>>

    companion object {

        fun getRetrofitInstance(path: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }
}'
//'https://api.github.com/search/repositories?q=language:Java&sort=stars& page=1