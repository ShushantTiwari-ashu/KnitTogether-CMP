import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    jvm()
    sourceSets {
        val jvmMain by getting  {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(project(":shared"))
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "KnitTogether-CMP"
            packageVersion = "1.0.0"
            vendor = "Shushant tiwari"

            windows {
                menuGroup = "KnitTogether-CMP"
                upgradeUuid = "KnitTogether-CMP"
                iconFile.set(project.file("icons.ico"))
            }
            macOS {
                packageVersion = "1.0"
                // a version only for the dmg package
                bundleID = "dev.shushant.knit_together"
                appStore = true
                iconFile.set(project.file("icons.icns"))
                dmgPackageVersion = "1.0"
                // a version only for the pkg package
                pkgPackageVersion = "1.0"

                // a build version for all macOS distributables
                packageBuildVersion = "1.0"
                // a build version only for the dmg package
                dmgPackageBuildVersion = "1.0"
                // a build version only for the pkg package
                pkgPackageBuildVersion = "1.0"
            }
            linux {
                iconFile.set(project.file("icons.png"))
            }
            outputBaseDir.set(project.buildDir.resolve("KnitTogether-CMP"))
        }
    }
}
