package com.example.cadastro_de_usuario.data.dto

import com.google.gson.annotations.SerializedName

class GitHubRepositoryListResponseDTO (
    @SerializedName("total_count") val totalCount: Long,
    @SerializedName("items") val items: List<GitHubRepositoryDTO>? = null,
)
