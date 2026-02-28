plugins {
    id("java-library")
    id("java-test-fixtures")
}

dependencies {
    implementation(project(":common:exceptionhandler"))
    implementation(project(":common:mapper"))
    implementation(project(":common:result"))

    implementation(project(":sandwich:application"))
    implementation(project(":sandwich:domain"))

    implementation("org.springframework:spring-web:7.0.0-M3")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.3") // TODO: Remove
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.4") // TODO: Refine

    testFixturesApi(testFixtures(project(":sandwich:domain")))

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.0")
    testImplementation("org.mockito:mockito-core:5.16.0")
    testImplementation("org.springframework:spring-test:7.0.0-M3")
    testImplementation("jakarta.servlet:jakarta.servlet-api:6.1.0")
}

