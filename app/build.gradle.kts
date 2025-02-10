plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.taskapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.taskapp"
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

    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    // Retrofit library
    implementation("com.squareup.retrofit2:retrofit:2.0.0-beta3")
    implementation("com.squareup.retrofit2:converter-gson:2.0.0")
    implementation("com.squareup.okio:okio:2.2.2")
    implementation("com.squareup.okhttp3:okhttp:3.0.0-RC1")
    implementation("com.squareup.okhttp3:logging-interceptor:3.8.0")

    // bumptech glide !!
    implementation("com.github.bumptech.glide:glide:4.15.0")
    implementation("com.github.bumptech.glide:okhttp3-integration:4.15.0")

    implementation ("com.google.android.material:material:1.0.0")
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    // - - ViewModel and LiveData
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0-RC")

    // RX Java
    implementation ("io.reactivex.rxjava2:rxkotlin:2.4.0")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation ("androidx.room:room-ktx:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.coordinatorlayout)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.viewpager2)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}