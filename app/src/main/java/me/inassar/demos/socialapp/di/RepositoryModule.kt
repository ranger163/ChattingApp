package me.inassar.demos.socialapp.di

import me.inassar.demos.socialapp.data.repository.AuthRepositoryImpl
import me.inassar.demos.socialapp.data.repository.ChatRepositoryImpl
import me.inassar.demos.socialapp.domain.repository.AuthRepository
import me.inassar.demos.socialapp.domain.repository.ChatRepository
import org.koin.dsl.module

/**
 * Repositories module
 * This DI module will be responsible of providing repositories dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Repositories module
 */
val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ChatRepository> { ChatRepositoryImpl(get(), get()) }
}