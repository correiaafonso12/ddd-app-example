plugins {
    id("java-library")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {
    api("jakarta.persistence:jakarta.persistence-api:3.2.0")
}
