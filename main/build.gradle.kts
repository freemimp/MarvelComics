import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.detekt)
}

android {
    namespace = "com.freemimp.main"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        val secureProps = Properties()
        val securePropsFile = rootProject.file("secure.properties")
        if (securePropsFile.exists()) {
            secureProps.load(FileInputStream(securePropsFile))
            buildConfigField("String", "publicApiKey", secureProps.getProperty("publicApiKey"))
            buildConfigField("String", "privateApiKey", secureProps.getProperty("privateApiKey"))
            buildConfigField("String", "baseUrl", secureProps.getProperty("baseUrl"))
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.comoseCompilerVersion.get()
    }
}

dependencies {
    implementation(libs.androidx.lifecycle.runtime.compose)
    coreLibraryDesugaring(libs.desugaring)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.lifecycleViewModelKtx)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.coil)
    implementation(libs.coilCompose)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.okhttp3LoggingInterceptor)
    implementation(libs.moshi)
    ksp(libs.moshiCodegen)
    implementation(libs.hilt)
    ksp(libs.hiltCodegen)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testImplementation (libs.mockk)
    testImplementation (libs.turbine)
    testImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}