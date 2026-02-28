plugins {
    id("java-library")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.0")
    testImplementation("org.mockito:mockito-core:5.16.0")
    testImplementation("org.springframework:spring-test:6.2.3")
}
