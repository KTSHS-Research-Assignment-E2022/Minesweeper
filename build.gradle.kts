plugins {
    kotlin("multiplatform") version "1.6.10"
    id("org.jetbrains.compose") version "1.2.0-alpha01-dev620"
}

version = "1.3.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
                implementation(compose.web.testUtils)
            }
        }
    }
}

tasks.register("copyToDocs") {
    dependsOn("jsBrowserProductionWebpack")

    val distributions = File("$rootDir\\build\\distributions")
    val docs = File("$rootDir\\docs")
    doFirst {
        distributions.copyRecursively(docs,true)
    }
}

tasks.wrapper {
    distributionUrl = "https://services.gradle.org/distributions/gradle-7.4.2-all.zip"
}
