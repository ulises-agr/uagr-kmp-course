This is a Kotlin Multiplatform project targeting Android, iOS.

* [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

com.uagr.kmp.course/
│
├── core/                       # Código global reutilizable (Cross-cutting concerns)
│   ├── network/                # Ktor client, DTOs compartidos, Network Result wrappers, Interceptors, Serializadores JSON, Headers.
│   ├── database/               # Room / SQLDelight, Drivers, Database Builder
│   ├── datastore/              # Preferences / Key-Value storage
│   ├── designsystem/           # UI reutilizable base (Theme, Color, Type, Atomic Components) (Botones custom, TopBars, Spinners de carga)
│   └── model/                  # Modelos de datos globales / Enums comunes
│
├── data/                       # Repositorios globales o compartidos entre varias features
│   └── repository/             # Ej. UserRepositoryImpl.kt
│
├── feature/                    # Pantallado o flujos de usuario aislados (logica de UI)
│   ├── auth/                   # Ejemplo de Feature: Login / Registro
│   │   ├── data/               # AuthRepositoryImpl, mappers y datasources específicos de Auth
│   │   ├── domain/             # AuthUseCase, AuthModel
│   │   └── ui/                 # AuthScreen.kt, AuthViewModel.kt, AuthState.kt (mantener la estructura estándar de Compose UDF -> State, Event, Effect y viewModel)
│   │
│   └── home/                   # Ejemplo de Feature: Home / Dashboard
│       ├── data/
│       ├── domain/
│       └── ui/
│           ├── components/
│           ├── model/
│           ├── state/
│           ├── viewmodel/
│           └── AuthScreen.kt       # En lugar de 'activity'
│
├── di/                         # Inyección de dependencias (Koin / Hilt / Metro)
│   └── AppModule.kt            # Módulos de Koin: networkModule, databaseModule, featureModules
│
└── App.kt                      # Entry point de Compose Multiplatform (NavHost base)

