plugins {
    id(Plugins.AGP.library)
    id(Plugins.Kotlin.android)
    id(Plugins.Kotlin.kapt)
}

android {
    namespace = "com.geektech.notes.data"
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
    }
}

dependencies {

    implementation(project(":domain"))

    implementation(Deps.UI.core)
    testImplementation(Deps.UI.junit)
    androidTestImplementation(Deps.UI.extJunit)

    //Room
    implementation(Deps.Room.ktx)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.runtime)

    //Javax inject
    implementation(Deps.Javax.inject)

    //Coroutines
    implementation(Deps.Coroutines.android)
}