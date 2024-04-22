apply("$rootDir/common.gradle")

plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.freemimp"

    defaultConfig {
        applicationId = "com.freemimp"
        targetSdk = libs.versions.compileSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.freemimp.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(project(":main"))
    implementation(project(":details"))
}
