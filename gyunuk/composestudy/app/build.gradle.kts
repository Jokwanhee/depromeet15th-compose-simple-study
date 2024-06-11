import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.bendeng.composestudy"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.bendeng.composestudy"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "KAKAO_TOKEN", getProperty("kakaotoken"))
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
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

fun getProperty(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {
    val nav_version = "2.7.7"
    val orbit_version = "8.0.0"
    val kakao_version = "2.20.1"
    val glide_version = "1.3.7"
    val retrofitVersion = "2.9.0"
    val okHttpVersion = "5.0.0-alpha.2"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //compose 버튼 아이콘 대체
    implementation("androidx.compose.material:material-icons-extended")

    //jetpack navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //orbit
    implementation("org.orbit-mvi:orbit-core:$orbit_version")
    implementation("org.orbit-mvi:orbit-viewmodel:$orbit_version")
    implementation("org.orbit-mvi:orbit-compose:$orbit_version")
    testImplementation("org.orbit-mvi:orbit-test:$orbit_version")

    //kakao
    implementation("com.kakao.sdk:v2-all:$kakao_version")

    //glide
    implementation ("com.github.skydoves:landscapist-glide:$glide_version")

    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    // retrofit
    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    api("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // okHttp
    api("com.squareup.okhttp3:okhttp:$okHttpVersion")
    api("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")
    api("com.squareup.okhttp3:okhttp-urlconnection:$okHttpVersion")
}