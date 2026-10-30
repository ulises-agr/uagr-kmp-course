# Integración de PackagesSdk en iOS mediante Swift Package Manager (SPM)

Este documento explica cómo compilar y consumir el módulo `packages-sdk` como un **Swift Package Manager (SPM)** independiente para Swift y SwiftUI.

---

## 1. Generar el XCFramework con Gradle

Para compilar el SDK de Kotlin Multiplatform a un archivo `.xcframework` compatible con iOS/Swift:

```bash
./gradlew :packages-sdk:assemblePackagesSdkReleaseXCFramework
```

Esto generará el framework compilado en la ruta:
`packages-sdk/build/XCFrameworks/release/PackagesSdk.xcframework`

---

## 2. Estructura de Swift Package Manager (`Package.swift`)

El archivo `Package.swift` dentro del módulo `packages-sdk` permite a Xcode importar la librería vía SPM:

```swift
// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "PackagesSdk",
    platforms: [
        .iOS(.v15)
    ],
    products: [
        .library(
            name: "PackagesSdk",
            targets: ["PackagesSdk"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "PackagesSdk",
            path: "./build/XCFrameworks/release/PackagesSdk.xcframework"
        )
    ]
)
```

> **Para publicación remota (GitHub / Gitlab):**
> Si vas a publicar el paquete en un repositorio Git independiente para distribución remota:
> 1. Comprime `PackagesSdk.xcframework` en un archivo `.zip`.
> 2. Súbelo a las Releases de GitHub/GitLab.
> 3. Reemplaza la ruta local por `url` y `checksum` en `Package.swift`:
> ```swift
> .binaryTarget(
>     name: "PackagesSdk",
>     url: "https://github.com/tu-usuario/packages-sdk-ios/releases/download/v1.0.0/PackagesSdk.xcframework.zip",
>     checksum: "<SHA-256-checksum>"
> )
> ```

---

## 3. Uso en Swift / SwiftUI

### Paso 3.1: Inicializar el SDK en AppDelegate / App Init

```swift
import SwiftUI
import PackagesSdk

@main
struct YourApp: App {
    init() {
        // Inicializa el SDK de Packages enviando la URL base y el token
        PackagesSdkIosKt.initPackagesSdkForIos(
            baseUrl: "https://server-dummy-hitss.onrender.com",
            accessToken: "TU_ACCESS_TOKEN",
            refreshToken: "TU_REFRESH_TOKEN",
            tokenProviderClosure: {
                // Opcional: Provee un cierre para refrescar tokens dinámicamente
                return PackagesTokens(accessToken: "NEW_TOKEN", refreshToken: "")
            }
        )
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
```

### Paso 3.2: Renderizar la pantalla de Packages en SwiftUI

```swift
import SwiftUI
import PackagesSdk

struct PackagesSdkView: UIViewControllerRepresentable {
    var onPackageClick: () -> Void = {}

    func makeUIViewController(context: Context) -> UIViewController {
        return PackagesSdkIosKt.PackagesViewController(onPackageClick: onPackageClick)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

// Ejemplo de vista SwiftUI que muestra el SDK de Packages
struct ContentView: View {
    var body: some View {
        NavigationView {
            PackagesSdkView(onPackageClick: {
                print("Paquete seleccionado")
            })
            .navigationTitle("Paquetes")
        }
    }
}
```
