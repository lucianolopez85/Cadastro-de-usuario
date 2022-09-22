package com.example.cadastro_de_usuario.data.dto

import com.google.gson.annotations.SerializedName

class GitHubResponseDTO (
    @SerializedName("Itens") val Itens: List<GitHubListDTO>
)

