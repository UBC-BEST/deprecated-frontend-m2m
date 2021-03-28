import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    kotlin("plugin.serialization")
    id("com.squareup.sqldelight")
    kotlin("kapt")
    id("kotlin-android")
    id("com.google.gms.google-services")
}

group = "com.example.tutorial"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.core:core-ktx:1.3.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.auth0.android:auth0:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.navigation:navigation-fragment:2.2.2")
    implementation("androidx.navigation:navigation-ui:2.2.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.2.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.2.2")
    implementation("com.google.firebase:firebase-auth:20.0.3")
    annotationProcessor("androidx.databinding:databinding-compiler:1.0.0")
    implementation("com.google.android.gms:play-services-auth:19.0.0")
    implementation ("com.google.firebase:firebase-bom:26.8.0")
}
android {
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.example.tutorial.android"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    lintOptions {
        isAbortOnError = false
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}