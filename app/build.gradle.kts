plugins {
    alias(libs.plugins.kapt)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
}

android {
    namespace = "pl.paullettuce.daznrecruitmenttask"
    compileSdk = 34

    defaultConfig {
        applicationId = "pl.paullettuce.daznrecruitmenttask"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // UI
    implementation(libs.bundles.core.ui)

    // Video player
    implementation(libs.bundles.player)

    // Rx
    implementation(libs.bundles.rx)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    // Room Database
    implementation(libs.bundles.room)
    kapt(libs.room.compiler)

    // Retrofit
    implementation(libs.bundles.retrofit)

    // Testing
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.test.android)
    androidTestImplementation(libs.bundles.test.android)
}