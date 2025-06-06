plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)

  alias(libs.plugins.ksp.devtools)
  alias(libs.plugins.hilt.android)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.mgoudarzi.movieapp"
  compileSdk = 35

  defaultConfig {
    applicationId = "com.mcoding.movieapp"
    minSdk = 27
    targetSdk = 35
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
  implementation(libs.material3)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  implementation(libs.androidx.material.icons.extended.android)

  implementation(libs.androidx.navigation.compose)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.hilt.android)
  ksp(libs.hilt.android.compiler)
  implementation(libs.androidx.hilt.navigation.compose)

  implementation(libs.coil.compose)
  implementation(libs.coil.network.okhttp)

  implementation(libs.retrofit)
  implementation(libs.converter.moshi)
  implementation(libs.moshi.kotlin)

  implementation(libs.readmore.material3)

  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx)
  ksp(libs.androidx.room.compiler)

  implementation(libs.androidx.datastore.preferences)
}
