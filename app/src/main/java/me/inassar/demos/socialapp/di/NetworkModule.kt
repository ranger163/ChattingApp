package me.inassar.demos.socialapp.di

import me.inassar.demos.socialapp.data.remote.source.AuthRemote
import me.inassar.demos.socialapp.data.remote.source.AuthRemoteImpl
import me.inassar.demos.socialapp.data.remote.source.ChatRemote
import me.inassar.demos.socialapp.data.remote.source.ChatRemoteImpl
import org.koin.dsl.module

/**
 * Network module
 * This DI module will be responsible of providing network dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Network module
 */
val networkModule = module {
    single<AuthRemote> { AuthRemoteImpl(get()) }
    single<ChatRemote> { ChatRemoteImpl(get()) }
}