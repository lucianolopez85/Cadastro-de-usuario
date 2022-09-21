package com.example.cadastro_de_usuario.domain.vo

data class UserDataVO(
    var id: Int = 0,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var type: Int = 0
)