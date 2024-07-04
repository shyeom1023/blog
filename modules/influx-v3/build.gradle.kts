plugins {
    id("org.springframework.boot") version Versions.springBoot
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")


    // influx
    implementation("com.influxdb:influxdb3-java:0.8.0")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
