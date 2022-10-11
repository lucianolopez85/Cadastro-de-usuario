package com.example.cadastro_de_usuario.app

import android.app.Application
import com.example.cadastro_de_usuario.di.gitHubModule
import com.example.cadastro_de_usuario.di.networkModule
import com.example.cadastro_de_usuario.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class GitHubApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GitHubApplication)
            modules(
                platformModule,
                networkModule,
                gitHubModule
            )
        }
    }
}