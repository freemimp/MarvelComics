// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.paparazzi) apply false
}
subprojects {
    plugins.withId("app.cash.paparazzi") {
        afterEvaluate {
            val constraints = dependencies.constraints
            constraints.add("testImplementation", "com.google.guava:guava") {
                attributes {
                    attribute(
                        TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                        objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM)
                    )
                }
                because("LayoutLib and sdk-common depend on Guava's -jre published variant. See https://github.com/cashapp/paparazzi/issues/906.")
            }
        }
    }
}
