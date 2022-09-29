package com.example.cadastro_de_usuario.domain.vo

data class GitHubListVO(
    val id: String,
    val avatar: String,
    val name: String,
    val description: String? = null,
    val star: String? = null,
    val fork: String? = null
)