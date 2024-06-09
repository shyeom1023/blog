import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Versions.springBoot
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")

    // mqtt
//    implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")
    implementation("org.springframework.integration:spring-integration-mqtt")
    implementation("org.springframework.integration:spring-integration-jmx")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
