plugins {
    java
}

group = "com.dfsek"
version = "0.1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") // Paper
    maven("https://repo.codemc.org/repository/maven-public/") // Terra
    maven("https://maven.devs.beer/") // ItemsAdder
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")

    compileOnly("com.dfsek.terra:api:6.6.1-BETA+83bc2c902")
    compileOnly("com.dfsek.terra:manifest-addon-loader:1.0.0-BETA+fd6decc70")
    compileOnly("com.dfsek.terra:structure-terrascript-loader:1.2.0-BETA+fd6decc70")

    compileOnly("dev.lone:api-itemsadder:4.0.9")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    processResources{
        expand(project.properties)

        inputs.property("version", rootProject.version)
        filesMatching("terra.addon.yml") {
            expand("version" to rootProject.version)
        }
    }
}
