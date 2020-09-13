import java.time.ZonedDateTime

// tag::plugins
plugins {
    kotlin("jvm") version "1.4.10"
}
// end::plugins

// tag::properties
group = "com.petrovich"
version = "0.2.0-SNAPSHOT"

val jacksonVersion = "2.11.2"
val junitVersion = "5.6.2"
// end::properties

// tag::repositories
repositories {
    mavenCentral()
}
// end::repositories

// tag::jar
tasks.jar {
    manifest {
        attributes["Created-By"] = "Dmitrii Kniazev"
        attributes["Implementation-Version"] = archiveVersion
        attributes["Build-Time"] = ZonedDateTime.now().toString()
    }
}
// end::jar

tasks.test {
    useJUnitPlatform()
}

// tag::dependencies
dependencies {
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    //test dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
// end::dependencies

// tag::source sets
sourceSets {
    main {
        resources.srcDir("petrovich-rules/")
        resources.include("gender.yml", "rules.yml")
    }
    test {
        resources.srcDir("petrovich-eval/")
        resources.include("*.tsv", "*.csv")
    }
}
// end::source sets
