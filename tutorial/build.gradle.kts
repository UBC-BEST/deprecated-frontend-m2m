buildscript {
    val kotlin_version by extra("1.4.20")
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.3")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:1.4.30")

    }
}
group = "com.example.tutorial"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
}

