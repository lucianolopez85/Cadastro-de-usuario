package com.example.cadastro_de_usuario.domain.converter

import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO

class GitHubListConverter {

    fun converter(data: List<GitHubRepositoryDTO>): List<GitHubListVO> =
        data.map { dto ->
            GitHubListVO(
            id = dto.id.toString(),
            avatar = dto.user.avatar,
            name = dto.name,
            description = dto.description,
            star = dto.star.toString(),
            fork = dto.fork.toString()
            )
        }
}