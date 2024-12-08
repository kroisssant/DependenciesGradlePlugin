plugins {
    `java-gradle-plugin`
    id("java")

    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.20"
    id ("maven-publish")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-properties:1.7.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

group = "com.kroissant"
version = 1.0

gradlePlugin {
    plugins {
        register("dependencyFromFile") {
            id = "com.kroissant.dependencyFromFile"
            implementationClass = "com.kroissant.GradlePlugin"
        }
    }

}

tasks.named<Test>("test") {
    // Use JUnit Jupiter for unit tests.
    useJUnitPlatform()
}

publishing {
    repositories {
        mavenLocal()
    }
}