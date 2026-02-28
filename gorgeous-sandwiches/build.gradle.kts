allprojects {

    repositories {
        mavenCentral()
    }

    tasks.withType<Jar> {
        val moduleName = project.path.replace(":", "-").replace(Regex("^-+"), "")
        archiveBaseName.set(moduleName)
    }

    tasks.withType<Test> {
        useJUnitPlatform()

        // Show standard output and error in the console
        testLogging {
            events("passed", "skipped", "failed")
            showStandardStreams = true
        }
    }

    plugins.withType<JavaPlugin> {
        extensions.configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }
    }
}
