import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	kotlin("jvm") version "1.8.22"
	kotlin("kapt") version "1.7.10"
	kotlin("plugin.spring") version "1.8.22"
	idea
}

group = "com.devilkim"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

subprojects {
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "kotlin")
	apply(plugin = "kotlin-kapt")
	dependencies {
		implementation("org.springframework.boot:spring-boot-starter:3.1.0")

		// JSON
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")

		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib")

		testImplementation("org.springframework.boot:spring-boot-starter-test")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
