plugins {
    id("java")
}

group = "com.devilkim"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-aop:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.0")

    // Apache
    implementation("commons-validator:commons-validator:1.7")
}

tasks.test {
    useJUnitPlatform()
}