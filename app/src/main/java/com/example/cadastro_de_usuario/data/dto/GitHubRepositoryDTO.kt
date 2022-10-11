package com.example.cadastro_de_usuario.data.dto

import com.google.gson.annotations.SerializedName

data class GitHubRepositoryDTO (
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("stargazers_count") val star: Long,
    @SerializedName("forks_count") val fork: Long,
    @SerializedName("owner") val user: UserDTO
)

data class UserDTO(
    @SerializedName("avatar_url") val avatar: String
)