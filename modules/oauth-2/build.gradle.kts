import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Versions.springBoot
//    id("org.springframework.boot") version "3.1.0"
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")

    // oauth default setting
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-oauth2-authorization-server:0.4.0")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-authorization-server")

//    implementation("org.springframework.security.oauth2:oauth2-resource-server:0.4.1")
//    implementation("org.springframework.security:spring-security-oauth2-jose")

    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
