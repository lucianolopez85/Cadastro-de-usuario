package com.example.cadastro_de_usuario.domain.converter

import com.example.cadastro_de_usuario.data.dto.GitHubListDTO
import com.example.cadastro_de_usuario.domain.vo.GitHubRepositoryVO

class ListRepositoryConverter {

    fun converter(data: List<GitHubListDTO>): List<GitHubRepositoryVO> =
        data.map { dto ->
            GitHubRepositoryVO(
            id = dto.id.toString(),
            avatar = dto.user.avatar,
            name = dto.name,
            description = dto.description,
            star = dto.star.toString(),
            fork = dto.fork.toString()
            )
        }
}