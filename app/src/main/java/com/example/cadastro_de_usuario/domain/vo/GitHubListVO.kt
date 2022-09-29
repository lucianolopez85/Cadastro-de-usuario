package com.example.cadastro_de_usuario.domain.vo

data class GitHubRepositoryVO(
    val id: String,
    val avatar: Int,
    val name: String,
    val description: String? = null,
    val star: String? = null,
    val fork: String? = null
)