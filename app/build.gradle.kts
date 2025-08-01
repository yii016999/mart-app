plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-kapt")
}

android {
    namespace = "com.sta.mart"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sta.mart"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    // java 17 is required for Compose
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    // Enable Compose
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}

// Configure Detekt for static code analysis
detekt {
    config.setFrom("$rootDir/detekt.yml")
    buildUponDefaultConfig = true
}

dependencies {
    // Core libraries
    implementation(libs.androidx.core.ktx)
    // lifecycle and viewmodel libraries
    implementation(libs.androidx.lifecycle.runtime.ktx)
    // Compose libraries
    implementation(libs.androidx.activity.compose)
    // use bom for Compose to manage versions
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material3.window.size)
    // unit testing libraries
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    // ui testing libraries
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.ui.test.junit4)
    // debugging and tooling libraries
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // hilt for dependency injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    // coroutines for asynchronous programming
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // navigation component for Compose
    implementation(libs.androidx.navigation.compose)
    testImplementation(kotlin("test"))
}
