import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version Versions.kotlin
    kotlin("kapt") version Versions.kotlin
    id("io.spring.dependency-management") version Versions.springDependencyManagement
    id("org.jetbrains.kotlin.plugin.spring") version Versions.kotlin
}

allprojects {
    group = "com.example"
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("io.spring.dependency-management")
        plugin("kotlin-spring")
    }
    dependencies {
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // junit
        testImplementation("org.junit.jupiter:junit-jupiter")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
//                jvmTarget = "11"
            }
        }

        withType<JavaCompile> {
            sourceCompatibility = "1.8"
            targetCompatibility = "1.8"
//            sourceCompatibility = "11"
//            targetCompatibility = "11"
        }

        withType<Test> {
            useJUnitPlatform()
            jvmArgs("--add-opens=java.base/java.nio=ALL-UNNAMED")
        }

    }
}

