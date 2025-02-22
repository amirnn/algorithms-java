plugins {
    id("java")
}

group = "com.blue"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:latest.release")
    testImplementation(platform("org.junit:junit-bom:latest.release"))
    testImplementation("org.junit.jupiter:junit-jupiter:latest.release")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:latest.release")
}

tasks.test {
    useJUnitPlatform()
}