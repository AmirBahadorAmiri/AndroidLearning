plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.room")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.amirbahadoramiri.androidlearning"
    compileSdk {
        version = release(36)
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
        dataBinding=true
    }
    dataBinding {
        enable=true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Persian Calender
    implementation("com.aminography:primecalendar:1.7.0")

    // RxJava RxAndroid
    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation("io.reactivex.rxjava3:rxjava:3.1.12")

    // Room Database
    val room_version = "2.8.4"
    implementation("androidx.room:room-runtime:${room_version}")
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-rxjava3:${room_version}")

    // Gson
    implementation("com.google.code.gson:gson:2.13.2")

    // Volley
    implementation("com.android.volley:volley:1.2.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.retrofit2:converter-moshi:3.0.0")
    implementation("com.squareup.retrofit2:adapter-rxjava3:3.0.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.3.2")

    // Moshi
    implementation("com.squareup.moshi:moshi:1.15.2")

    // Okio
    implementation("com.squareup.okio:okio:3.16.4")

    // Picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    // Glide
    implementation("com.github.bumptech.glide:glide:5.0.5")

    // Fresco
    implementation("com.facebook.fresco:fresco:3.6.0")

    // dagger 2
    implementation("com.google.dagger:dagger-android:2.57.2")
    annotationProcessor("com.google.dagger:dagger-android-processor:2.57.2")

    // ion networking and load images and downloading files
    implementation("com.koushikdutta.ion:ion:3.1.0")

    // circle image view
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // circle image view
    implementation("com.mikhaellopez:circularimageview:4.3.1")

}