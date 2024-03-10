import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version Versions.springBoot
}

dependencies {
    implementation(project(":benchmark-def"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    // crawling lib
    implementation("org.jsoup:jsoup:1.17.2")
    // excel lib
    implementation("org.apache.poi:poi-ooxml:5.2.5")



    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
