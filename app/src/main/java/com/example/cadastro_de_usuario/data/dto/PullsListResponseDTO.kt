package com.example.cadastro_de_usuario.data.dto

import com.google.gson.annotations.SerializedName

data class PullsListResponseDTO (
    @SerializedName("pulls") val pulls: List<PullsListDTO>
)