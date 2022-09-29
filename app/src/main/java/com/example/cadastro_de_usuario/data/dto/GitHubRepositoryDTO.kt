package com.example.cadastro_de_usuario.data.dto

import com.google.gson.annotations.SerializedName

data class GitHubRepositoryDTO (
    @SerializedName("id") val id: Int,
    @SerializedName("owner") val user: User,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("stargazers_count") val star: Int,
    @SerializedName("forks_count") val fork: Int
)

data class User(
    @SerializedName("avatar_url") val avatar: String
)