package com.example.cadastro_de_usuario.domain.vo

internal data class GitHubListVO(
    val id: Long,
    val nameRepository: String,
    val avatar: String,
    val description: String?,
    val star: String,
    val fork: String
)