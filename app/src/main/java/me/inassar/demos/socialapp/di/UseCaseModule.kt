package me.inassar.demos.socialapp.di

import me.inassar.demos.socialapp.domain.usecase.FriendListUseCase
import me.inassar.demos.socialapp.domain.usecase.GetRoomHistoryUseCase
import me.inassar.demos.socialapp.domain.usecase.LoginUseCase
import me.inassar.demos.socialapp.domain.usecase.SignupUseCase
import org.koin.dsl.module

/**
 * Repositories module
 * This DI module will be responsible of providing use case dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Repositories module
 */
val useCaseModule = module {
    single { LoginUseCase(get()) }
    single { SignupUseCase(get()) }
    single { FriendListUseCase(get()) }
    single { GetRoomHistoryUseCase(get()) }
}