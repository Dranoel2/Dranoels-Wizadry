plugins {
    id("fabric-loom")
    `maven-publish`
    java
}

group = property("maven_group")!!
version = property("mod_version")!!

val minecraft_version: String by project
val yarn_mappings: String by project
val loader_version: String by project
val fabric_kotlin_version: String by project
val fabric_api_version: String by project
val cardinal_components_version: String by project

repositories {
    maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    maven("https://ladysnake.jfrog.io/artifactory/mods")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$yarn_mappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loader_version")

    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabric_api_version")
    modImplementation("software.bernie.geckolib:geckolib-fabric-1.17:3.0.29:dev")
    modApi("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-base:$cardinal_components_version")
    modApi("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-util:$cardinal_components_version")
    modApi("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-item:$cardinal_components_version")
    modApi("io.github.onyxstudios.Cardinal-Components-API:cardinal-components-entity:$cardinal_components_version")
}

tasks {

    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand(mutableMapOf("version" to project.version))
        }
    }

    jar {
        from("LICENSE")
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifact(remapJar) {
                    builtBy(remapJar)
                }
            }
        }

        // select the repositories you want to publish to
        repositories {
            // uncomment to publish to the local maven
            // mavenLocal()
        }
    }
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()
}



// configure the maven publication
