package com.example.cadastro_de_usuario.domain.converter

import com.example.cadastro_de_usuario.data.dto.GitHubListDTO
import com.example.cadastro_de_usuario.domain.vo.GitHubRepositoryVO

class ListRepositoryConverter() {

    fun converter(data: List<GitHubListDTO>): List<GitHubRepositoryVO> =
        data.map { dto ->
            GitHubRepositoryVO(
            id = dto.id,
            avatar = dto.avatar,
            name = dto.name,
            description = dto.description,
            star = dto.star,
            fork = dto.fork
            )
        }
}