package com.example.cadastro_de_usuario.domain.converter

import com.example.cadastro_de_usuario.data.dto.GitHubRepositoryDTO
import com.example.cadastro_de_usuario.data.dto.PullsListDTO
import com.example.cadastro_de_usuario.domain.vo.GitHubListVO
import com.example.cadastro_de_usuario.domain.vo.ListPullsVO
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

internal class RepoConverter {

    private companion object {
        val LOCATE = Locale("it", "IT")
        const val PATTERN = "#,###.##"
    }

    fun convert(data: GitHubRepositoryDTO): GitHubListVO {
        val stargazersAmount = data.star.toFormat()
        val forksAmount = data.fork.toFormat()

        return GitHubListVO(
            id = data.id,
            nameRepository = data.name,
            avatar = data.user.avatar,
            description = data.description,
            fork = forksAmount,
            star = stargazersAmount
        )
    }

    private fun Number.toFormat() =
        DecimalFormat(
            PATTERN, DecimalFormatSymbols.getInstance(LOCATE)
        ).format(this)
}
