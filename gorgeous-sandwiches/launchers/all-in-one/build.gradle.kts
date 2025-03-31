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

dependencies {
    runtimeOnly(project(":core:boot"))

    runtimeOnly(project(":sandwich:api"))
    runtimeOnly(project(":sandwich:persistence"))
}

springBoot {
    mainClass.set("app.ddd.gsandwiches.core.boot.GsandwichesApplication")
}
