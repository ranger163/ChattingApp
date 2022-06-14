# SocialApp

## Introduction

This is a [PROJECT IVORY](https://github.com/ranger163/ProjectIvory) repository for a picked up free design from [uplabs.com](https://www.uplabs.com/posts/social-app-ui-2) website written
in kotlin using MVVM design pattern that containes 5 screens "Login, Signup, forgot password, friend list and chat room" screens. I've implemented each screen and it's
functions  end to end from scratch server side and client side with CLEAN Archtecture and made it ready to be customized on your project if you wish to use it just clone
this project and start customizing.

It's a playground application I've been doing on my spare time to play with jetpack compose, compose material 3 and having a socket connection that allows users to chat and have conversations
with each other, also I've written it's backend server side using [Ktor server](https://ktor.io) and [Kmongo DB](https://litote.org/kmongo) as my database.

To have a full cycle working application (celint and server) please clone the [social-app-server](https://github.com/ranger163/social-app-server) and do what's listed there in the read me file and have fun with it.

## Application features

- Fully support of dark/light theme.
- Fully support of android 12 material 3.
- User can signup with his information and create new account.
- User can login to his already created account.
- Forgot password screen is implemented only as design (I was too lazy to do it's backend work :D ).
- User can find all already registered users and can start chatting with any one.
- User can pick any other user from his friend list and start chatting with him.
- User will find his history messages whenever he gets back again to the same friend room.

## Requirements

This module requires the following modules/libraries:


### User Interface

* [Androidx](https://developer.android.com/jetpack/androidx)
* [Coil image loader](https://coil-kt.github.io/coil)
* [Android Jetpacke Compose](https://developer.android.com/jetpack/compose)
* [Android Compose Material 3](https://developer.android.com/jetpack/androidx/releases/compose-material3)

### Archtecture

* [Android ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [Coroutines](https://developer.android.com/kotlin/coroutines)
* [Koin Dependency Injection](https://insert-koin.io)
* [Ktor Network Client](https://ktor.io)
* [Kotlin Flow](https://developer.android.com/kotlin/flow)

### Common

* [Kotlin](https://kotlinlang.org)

## Installation

Install as usual,
* Clone this repo.
* Update your Android Studio Electric Eel | 2022.1.1 Canary .
* Happy coding.
* Support me by staring this repo and following me for more projects.

## Screen Shoots

<img src="https://github.com/ranger163/SocialApp/blob/master/screenshots/login.jpg" width="300" height="650"> <img src="https://github.com/ranger163/SocialApp/blob/master/screenshots/signup.jpg" width="300" height="650">
<img src="https://github.com/ranger163/SocialApp/blob/master/screenshots/forgot_password.jpg" width="300" height="650"> <img src="https://github.com/ranger163/SocialApp/blob/master/screenshots/friend_list.jpg" width="300" height="650">
<img src="https://github.com/ranger163/SocialApp/blob/master/screenshots/chat_room.jpg" width="300" height="650"> <img src="https://github.com/ranger163/SocialApp/blob/master/screenshots/chat_room_keyboard.jpg" width="300" height="650">

## Hire Me
I'm ready for a full-time or a freelancing job, just drop me an email [here](https://www.inassar.me) and let's do our chating.

## License
Made with :heart: by [Ahmed Nassar](https://github.com/ranger163), licensed under the [MIT License](https://github.com/ranger163/SocialApp/blob/master/license)
