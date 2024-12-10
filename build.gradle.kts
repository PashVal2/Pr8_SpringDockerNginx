plugins {
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.8.20"
    kotlin("plugin.spring") version "1.8.20"
}

group = "org.example"
version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("junit:junit:4.13.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("org.springframework.boot:spring-boot-starter-security");

    // Для работы с BCrypt (для хеширования паролей)
    implementation("org.springframework.security:spring-security-crypto");
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.8.2")

    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.session:spring-session-data-redis")

    implementation("org.yaml:snakeyaml:1.28")
    implementation("com.github.javafaker:javafaker:1.0.2")

    implementation("org.jfree:jfreechart:1.5.3")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

springBoot {
    mainClass.set("org.example.Main")  // Укажите правильный путь к основному классу
}

tasks.withType<Test> {
    useJUnitPlatform()
}
