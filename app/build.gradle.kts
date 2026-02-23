plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.room")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.amirbahadoramiri.androidlearning"
    compileSdk {
        version = release(36)
    }

    packaging {
        resources {
            excludes += "META-INF/INDEX.LIST"
            excludes += "META-INF/io.netty.versions.properties"
        }
    }

    defaultConfig {
        applicationId = "com.amirbahadoramiri.androidlearning"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    room {
        schemaDirectory("$projectDir/schemas")
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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    buildFeatures {
        dataBinding = true
    }
    dataBinding {
        enable = true
    }

    kotlin {
        jvmToolchain(21)
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.fragment)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Persian Calender
    implementation(libs.primecalendar)

    // RxJava RxAndroid
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    // Room Database
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.rxjava3)

    // Gson
    implementation(libs.gson)

    // Volley
    implementation(libs.volley)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.moshi)
    implementation(libs.adapter.rxjava3)
    implementation(libs.converter.jackson)
    implementation(libs.converter.scalars)

    // OkHttp
    implementation(libs.okhttp)

    // Moshi
    implementation(libs.moshi)

    // Okio
    implementation(libs.okio)

    // Picasso
    implementation(libs.picasso)

    // Glide
    implementation(libs.glide)

    // Fresco
    implementation(libs.fresco)
    implementation(libs.infer.annotation)


//    // dagger 2
//    implementation("com.google.dagger:dagger-android:2.59.1")
//    annotationProcessor("com.google.dagger:dagger-android-processor:2.59.1")

//    hilt android
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)


    // ion networking and load images and downloading files
    implementation(libs.ion)

    // circle image view
    implementation(libs.circleimageview)

    // circle image view
    implementation(libs.circularimageview)

    // jackson json converter
    implementation(libs.jackson.core)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.annotations)

    // mapir
    implementation(libs.android.sdk)

//    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")
//    implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1")
//    implementation("androidx.localbroadcastmanager:localbroadcastmanager:1.1.0")

//    mqtt server
    implementation(libs.hivemq.mqtt.client)

//    eventbus
    implementation(libs.eventbus)

}