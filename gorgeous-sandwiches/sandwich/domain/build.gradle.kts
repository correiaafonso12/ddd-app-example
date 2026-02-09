plugins {
    id("java-library")
    id("java-test-fixtures")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.17.0")
    api(project(":core:spi"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.0")
    testImplementation("org.mockito:mockito-core:5.16.0")
}
