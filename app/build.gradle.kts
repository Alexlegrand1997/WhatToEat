import java.util.Properties


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0"
    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
    id("com.google.dagger.hilt.android")

}


android {
    namespace = "com.example.whattoeat"
    compileSdk = 34

    defaultConfig {
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        buildConfigField(
            "String",
            "SPOONACULAR_API_KEY",
            properties.getProperty("SPOONACULAR_API_KEY")
        )

        applicationId = "com.example.whattoeat"
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    android.buildFeatures.buildConfig = true

    //For exportSchema true
//    ksp {
//        arg("room.schemaLocation", "$projectDir/schemas")
//    }


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
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx)
//    implementation(libs.androidx.ui.desktop)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Bibliothèque Fuel pour les requêtes HTTP
    implementation(libs.fuel.android)
    implementation(libs.fuel.json)

    //Serialization for JSON
    implementation(libs.kotlinx.serialization.json)

    // Extension to load Image from web with the help of Async
    implementation(libs.coil.compose)

    // Implementation of ROOM to save data in local storage

    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.room.testing)
    // Need KTX for the coroutine of ROOM
    implementation(libs.androidx.room.ktx)

    // Implementation for Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Implementation for DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

}
