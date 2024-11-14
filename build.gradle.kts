plugins {
    kotlin("jvm") version "2.0.10"
    id("org.jetbrains.dokka") version "1.9.20"
}

tasks.dokkaHtml {
    outputDirectory.set(file("C:\\Users\\Павел\\IdeaProjects\\kotlin-telegram-bot-amaru\\build\\dokka\\html"))

    dokkaSourceSets {
        configureEach {
            // Пропускать элементы без документации
            reportUndocumented = true
            skipDeprecated = true
        }
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.mockito:mockito-core:4.3.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")

    implementation("org.telegram:telegrambots:6.3.0")
    implementation("org.telegram:telegrambotsextensions:6.1.0")
}

tasks.test {
    useJUnitPlatform()
}