import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("java-library")
    id("maven-publish")
    id("com.github.jmongard.git-semver-plugin")
}

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/MDL-Viewer/hott-model")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    api("de.treichels.hott:hott-model:_")
}

semver {
    releaseTagNameFormat = "v%s"
}

version = semver.version

tasks {
    jar {
        manifest {
            attributes (
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
            )
        }
    }

    withType(KotlinCompile::class) {
        //kotlinOptions.freeCompilerArgs = listOf("-opt-in=kotlin.ExperimentalUnsignedTypes")
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/mdlviewer/hott-serial")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("github") {
            artifactId = project.name.lowercase()
            group = "de.treichels.hott"
            from(components["java"])
        }          
    }
}