package com.example.cadastro_de_usuario.commons

internal sealed class UiAction<out T>

internal object LoadAction : UiAction<Nothing>()
internal data class ErrorAction(val data: Throwable) : UiAction<Nothing>()
internal data class SuccessAction<T>(val data: T) : UiAction<T>()