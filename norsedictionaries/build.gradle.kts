plugins {
    kotlin("jvm") version "1.8.0"
    application
    kotlin("plugin.serialization") version "1.5.31"
}

group = "io.github.stscoundrel"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("org.xerial:sqlite-jdbc:3.34.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}
