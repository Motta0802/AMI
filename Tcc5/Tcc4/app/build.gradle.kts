plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.tcc3"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tcc3"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildToolsVersion = "35.0.1"
    viewBinding {
        enable = true
    }
}

dependencies {

    val room_version = "2.6.1"

    annotationProcessor ("androidx.room:room-compiler:$room_version")

    implementation("androidx.room:room-runtime:$room_version")
    implementation ("androidx.lifecycle:lifecycle-viewmodel:$room_version")
    implementation ("androidx.activity:activity:1.4.0")
    implementation ("androidx.room:room-runtime:$room_version")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}