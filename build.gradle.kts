import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "de.lathspell.test"
version = "0.0.1-SNAPSHOT"

plugins {
    val kotlinVersion = "1.2.61"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
    id("org.springframework.boot") version "2.0.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    "implementation"("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    "implementation"("org.jetbrains.kotlin:kotlin-reflect")

    "compileOnly"("org.springframework.boot:spring-boot-devtools")
    "testImplementation"("org.springframework.boot:spring-boot-starter-test")

    "implementation"("org.springframework.boot:spring-boot-starter-data-rest")
    "implementation"("org.springframework.boot:spring-boot-starter-actuator")

    "implementation"("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Retrofit HTTP Client
    "implementation"("com.squareup.retrofit2:retrofit:2.4.0")
    "implementation"("com.squareup.retrofit2:converter-jackson:2.4.0")

    // Fuel native-kotlin HTTP Client (couldn"t get to work)
    // implementation("com.github.kittinunf.fuel:fuel:1.14.0")
    // implementation("com.github.kittinunf.fuel:fuel-jackson:1.14.0")

    // Swagger
    "implementation"("io.springfox:springfox-swagger2:2.7.0")
    "implementation"("io.springfox:springfox-swagger-ui:2.7.0")
}
