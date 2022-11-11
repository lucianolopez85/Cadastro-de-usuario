package com.example.cadastro_de_usuario.domain.converter

import com.example.cadastro_de_usuario.data.dto.PullsListDTO
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO

class PullsRepoConverter {

    fun convert(data: List<PullsListDTO>): List<ListPullsVO> {
        return data.map { DTO->
            ListPullsVO(title = DTO.title)
        }
    }
}