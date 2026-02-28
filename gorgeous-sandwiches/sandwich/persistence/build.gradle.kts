plugins {
    id("java-library")
    id("java-test-fixtures")
}

dependencies {
    implementation(project(":common:mapper"))

    implementation(project(":core:spi"))

    implementation(project(":sandwich:domain"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.4") // TODO: This pulls loads of deps. try to workaround it
    runtimeOnly("com.h2database:h2:2.3.232")
    runtimeOnly("org.postgresql:postgresql:42.7.5") // TODO: Separate drivers

    testFixturesApi(testFixtures(project(":sandwich:domain"))) // TODO: Duplicate? See what to do

    testImplementation("org.junit.jupiter:junit-jupiter:5.12.0")
    testImplementation("org.junit.platform:junit-platform-launcher:1.12.0")
    testImplementation("org.mockito:mockito-core:5.16.0")
}
