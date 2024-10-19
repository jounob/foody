plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    //    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")

    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.foodyapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodyapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    //    implementation (fileTree(dir: "libs", include: ["*.jar"]))

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    implementation ("com.google.android.material:material:1.12.0")

    // Navigation Component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.8.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.8.1")

    //     Room components
    implementation ("androidx.room:room-runtime:2.6.1")
    ksp ("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.room:room-ktx:2.6.1")
    androidTestImplementation ("androidx.room:room-testing:2.6.1")

    // Data binding
    //    kapt "com.android.databinding:compiler:3.2.0-alpha10"
    //    kapt "androidx.databinding:databinding-common:4.2.2"

    // DataStore
    implementation ("androidx.datastore:datastore-preferences:1.0.0")

    // Recyclerview
    implementation ("androidx.recyclerview:recyclerview:1.3.2")

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Dagger - Hilt
    //    ksp("com.google.dagger:dagger-compiler:2.52")
    ksp("com.google.dagger:hilt-compiler:2.52")
    implementation ("com.google.dagger:hilt-android:2.52")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

    // Image Loading library Coil
    implementation ("io.coil-kt:coil:2.3.0")

    // Gson
    implementation ("com.google.code.gson:gson:2.10.1")

    // Shimmer
    implementation ("com.facebook.shimmer:shimmer:0.5.0")

    // Jsoup
    implementation ("org.jsoup:jsoup:1.16.1")

    //Glide
    //    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //    ksp("com.github.bumptech.glide:compiler:4.16.0")
    //    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
}
