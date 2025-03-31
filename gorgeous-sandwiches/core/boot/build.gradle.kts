plugins {
    id("java-library")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:3.4.4")
}
