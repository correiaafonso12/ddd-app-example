plugins {
    id("java-library")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {
    implementation("org.springframework:spring-context:6.2.3")
    implementation("org.springframework:spring-web:7.0.0-M3")
    implementation("org.springframework.boot:spring-boot-autoconfigure:3.4.13")
    implementation(project(":core:spi"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.0")
}
