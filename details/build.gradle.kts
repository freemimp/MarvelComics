apply("$rootDir/common.gradle")

plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.freemimp.details"
}

dependencies {
    implementation(project(":main"))
}
