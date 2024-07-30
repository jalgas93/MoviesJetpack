plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
//    alias(libs.plugins.hiltAndroid)

}

android {
    namespace = "com.example.moviesjetpackcompose"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviesjetpackcompose"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
//    kapt {
//        correctErrorTypes = true
//        javacOptions {
//            // These options are normally set automatically via the Hilt Gradle plugin, but we
//            // set them manually to workaround a bug in the Kotlin 1.5.20
//            option("-Adagger.fastInit=ENABLED")
//            option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
//        }
//    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.compose.material)

    //Navigation Voyager
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.tab)
    implementation(libs.voyager.transitions)
//    implementation(libs.voyager.screenmodel)
    implementation(libs.voyager.bottom)

    //lifecycle-viewmodel-compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //Network
    implementation(libs.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.okhttp)

    // Kotlin serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.converter.gson)

    //hilt
//    implementation(libs.hilt.android)
////    kapt("androidx.hilt:hilt-compiler:2.44")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
////    kapt ("com.google.dagger:hilt-android-compiler:2.49")
    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-compiler:2.49")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("io.coil-kt:coil-compose:2.6.0")
}