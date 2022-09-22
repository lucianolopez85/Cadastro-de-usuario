package com.example.cadastro_de_usuario.data.repository

import com.example.cadastro_de_usuario.R
import com.example.cadastro_de_usuario.data.dto.GitHubListDTO
import com.example.cadastro_de_usuario.data.dto.GitHubResponseDTO
import kotlinx.coroutines.flow.flow

class GitHubRepository(
//    private val api: GitHubRepositoryApi
) {
    fun getListRepository() =
//        api.getListRepository(id)

        flow{ emit(
            GitHubResponseDTO(
                Itens =
                listOf(
                    GitHubListDTO(
                        id = "101",
                        avatar = R.mipmap.ic_launcher,
                        name = "Goku",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed molestie, urna varius rhoncus auctor, lectus justo vehicula leo, ut dictum sem nisi ac leo. Quisque neque diam, volutpat non urna non, commodo varius mi. Nunc dictum maximus turpis, ut sagittis velit.",
                        star = "2,1k",
                        fork = "1,7k"
                    ),
                    GitHubListDTO(
                        id = "101",
                        avatar = R.mipmap.ic_launcher,
                        name = "Goku",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed molestie, urna varius rhoncus auctor, lectus justo vehicula leo, ut dictum sem nisi ac leo. Quisque neque diam, volutpat non urna non, commodo varius mi. Nunc dictum maximus turpis, ut sagittis velit.",
                        star = "2,1k",
                        fork = "1,7k"
                    ),
                    GitHubListDTO(
                        id = "101",
                        avatar = R.mipmap.ic_launcher,
                        name = "Goku",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed molestie, urna varius rhoncus auctor, lectus justo vehicula leo, ut dictum sem nisi ac leo. Quisque neque diam, volutpat non urna non, commodo varius mi. Nunc dictum maximus turpis, ut sagittis velit.",
                        star = "2,1k",
                        fork = "1,7k"
                    ),
                    GitHubListDTO(
                        id = "101",
                        avatar = R.mipmap.ic_launcher,
                        name = "Goku",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed molestie, urna varius rhoncus auctor, lectus justo vehicula leo, ut dictum sem nisi ac leo. Quisque neque diam, volutpat non urna non, commodo varius mi. Nunc dictum maximus turpis, ut sagittis velit.",
                        star = "2,1k",
                        fork = "1,7k"
                    ),
                    GitHubListDTO(
                        id = "101",
                        avatar = R.mipmap.ic_launcher,
                        name = "Goku",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed molestie, urna varius rhoncus auctor, lectus justo vehicula leo, ut dictum sem nisi ac leo. Quisque neque diam, volutpat non urna non, commodo varius mi. Nunc dictum maximus turpis, ut sagittis velit.",
                        star = "2,1k",
                        fork = "1,7k"
                    ),
                )
            )
        )}
}