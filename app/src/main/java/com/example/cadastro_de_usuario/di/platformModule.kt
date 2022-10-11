package com.example.cadastro_de_usuario.di

import android.content.res.Resources
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val platformModule = module {
    single<Resources> { androidContext().resources }
}
