<h1 align="center">Cryptprika</h1> 

<p align="center">
Android crypto app This app was created to learn more about Clean architecture, the use of coil for
image consumption, among other things.

![App Screens](app/app-screens.png)
</p>

## Installation

Clone this repository and import into **Android Studio**

```bash
git clone https://github.com/munbonecci/Cryptprika.git
```

## Build variants

Use the Android Studio *Build Variants* button to choose between **production** and **staging**
flavors combined with debug and release build types

## Maintainers

This project is maintained by:

* [Edmundo Bonequi](http://github.com/munbonecci)

## Architecture

This App is based on the MVVM architecture and the Repository pattern and it has the approach in Clean Architecture where we can manage different layers.

## Built with

- [Kotlin](https://kotlinlang.org/) -for coding
- [Coil](https://coil-kt.github.io/coil/) - for loading images from network.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Coroutines](https://developer.android.com/kotlin/coroutines) - for asynchronous calls.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - Construct the REST APIs.
- [Cypher](https://github.com/sqlcipher/android-database-sqlcipher) - To encrypt the database
- Jetpack
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Observe Android lifecycles and handle UI states upon the lifecycle changes.
    - [View Binding](https://developer.android.com/topic/libraries/view-binding) - Feature that allows you to more easily write code that interacts with views.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started) - Handling and manage the navigation in the app.
    - [Room Persistence](https://developer.android.com/jetpack/androidx/releases/room) - Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
- Lottie animation
- [Mockito-Kotlin](https://github.com/mockito/mockito-kotlin#mockito-kotlin) - Library for unit testing based in kotlin.


## How I run the app?
- Clone the repository
- Open it in Android Studio
- Wait until dependencies are installed
- Run app in your emulator or physical device

## Api used
This app use CoinPaprika Api, api token or key is not needed
- [CoinPaprika](https://api.coinpaprika.com/)