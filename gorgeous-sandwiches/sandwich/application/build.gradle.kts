plugins {
    id("java-library")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

dependencies {
    implementation(project(":common:result"))
    implementation(project(":core:exceptions"))

    implementation(project(":sandwich:domain"))

    implementation("org.springframework:spring-context:6.2.3")

    testImplementation(testFixtures(project(":sandwich:domain")))  // TODO: Duplicate? See what to do

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.0")
    testImplementation("org.mockito:mockito-core:5.16.0")
}
