package me.inassar.demos.socialapp.di

import me.inassar.demos.socialapp.common.SessionPrefs
import me.inassar.demos.socialapp.common.SessionPrefsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Cache module
 * This DI module will be responsible of providing caching dependencies
 * which need to be live as long as app is living
 * @constructor Create empty Cache module
 */
val cacheModule = module {
    single <SessionPrefs>{ SessionPrefsImpl(androidContext()) }
}