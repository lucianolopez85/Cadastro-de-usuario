package com.example.cadastro_de_usuario.data.dto

import com.google.gson.annotations.SerializedName

data class GitHubListDTO (
    @SerializedName("id") val id: String,
    @SerializedName("avatar") val avatar: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("star") val star: String,
    @SerializedName("fork") val fork: String
)