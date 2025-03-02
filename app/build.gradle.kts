plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.ktorfit)
    alias(libs.plugins.room)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

android {
    namespace = "miao.kmirror.wanndroid.compose"
    compileSdk = 35

    defaultConfig {
        applicationId = "miao.kmirror.wanndroid.compose"
        minSdk = 21
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
//        allprojects {
//            tasks.withType<JavaCompile> {
//                options.compilerArgs.add("-Xlint:deprecation")
//            }
//        }
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    // Compile time check
    ksp {
        arg("KOIN_CONFIG_CHECK", "true")
        arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
    }

    room {
        schemaDirectory("$projectDir/schemas")
        generateKotlin = true
    }
}

dependencies {

    implementation(libs.room.runtime.android)
    ksp(libs.room.compiler)

    implementation(libs.ulid.kotlin)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.ktor3)

    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    implementation(libs.koin.androidx.compose)
    implementation(libs.androidx.webkit)
    ksp(libs.koin.ksp)

    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.ktorfit.ktor.lib)
    implementation(libs.ktor.client.logging)
//    implementation(libs.ktorfit.ktor.converters.call)
//    implementation(libs.ktorfit.ktor.converters.flow)
//    implementation(libs.ktorfit.ktor.converters.response)

    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client.okhttp)

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
}
