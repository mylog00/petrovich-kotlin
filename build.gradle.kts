import java.util.Date

// tag::plugins
plugins {
    kotlin("jvm") version "1.3.72"
}
// end::plugins

// tag::properties
group = "com.petrovich"
version = "0.2.1-SNAPSHOT"

val jacksonVersion = "2.9.2"
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
        attributes["Build-Time"] = Date().toString()
    }
}
// end::jar

tasks.test {
    useJUnitPlatform()
}

// tag::dependencies
dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    //test dependencies
    testImplementation(kotlin("test"))
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

// tag::kotlin-compile
tasks.compileKotlin {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}
tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}
// end::kotlin-compile
