import java.io.FileInputStream
import java.util.Properties

apply("$rootDir/common.gradle")

plugins {
    alias(libs.plugins.androidLibrary)
}

android {
    namespace = "com.freemimp.main"

    defaultConfig {
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

    buildFeatures {
        buildConfig = true
    }
}
