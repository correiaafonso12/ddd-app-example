plugins {
	id("java")
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "app.ddd"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {  // TODO: Split boot and runnable dist
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.4")

    runtimeOnly(project(":sandwich:api"))
    runtimeOnly(project(":sandwich:persistence"))

    // // Spring Security Oauth2 
    // implementation 'org.springframework.boot:spring-boot-starter-security'
    // implementation 'org.springframework.security:spring-security-oauth2-client'
    // implementation 'org.springframework.boot:spring-boot-starter-oauth2-resource-server'

    // //Keycloak
    // implementation 'org.keycloak:keycloak-admin-client:24.0.5'

    // //Healthchecks
    // implementation 'org.springframework.boot:spring-boot-starter-actuator'

    // // Other
    // implementation 'no.gorandalum:fluent-result:1.5.0'
}
