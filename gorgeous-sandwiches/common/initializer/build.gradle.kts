plugins {
    id("java-library")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {
	implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")
}
