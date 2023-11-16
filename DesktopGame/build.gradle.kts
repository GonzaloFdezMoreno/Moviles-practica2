plugins {
    id("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}
dependencies {
    implementation(project(mapOf("path" to ":DesktopEngine")))
    implementation(project(mapOf("path" to ":Logic")))
    implementation(project(mapOf("path" to ":CommonEngine")))
}
