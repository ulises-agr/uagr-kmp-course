// swift-tools-version: 5.9
// Package.swift - Swift Package Manager manifest for PackagesSdk

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
            path: "./build/XCFrameworks/release/PackagesSdk.xcframework",
        )
    ]
)
