pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google {
            content {
                includeGroupByRegex("androidx.*")
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
            }
        }
        // Resolves the app.bugscreen:android / android-no-op SDK artifacts.
        mavenCentral()
    }
}

plugins {
    // Auto-provisions the JDK 17 toolchain the build compiles with, even if it isn't
    // installed locally (see kotlin { jvmToolchain(17) } in app/build.gradle.kts).
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "BugScreen Travel"
include(":app")
