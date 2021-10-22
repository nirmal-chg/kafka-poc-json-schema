plugins {

	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.31"
	kotlin("plugin.spring") version "1.5.31"
	jacoco
}

group = "com.playpen"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {

	compileOnly {

		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {

	mavenCentral()
	maven {

		url = uri("https://packages.confluent.io/maven/")
	}
	maven {

		url = uri("https://repository.mulesoft.org/nexus/content/repositories/public/")
	}
}

dependencies {

	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	implementation("io.confluent:kafka-json-schema-serializer:6.2.1")
	implementation("com.github.everit-org.json-schema:org.everit.json.schema:1.12.2")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.5")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
	testImplementation("io.mockk:mockk:1.12.0")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:4.6.3") // for kotest framework
	testImplementation("io.kotest:kotest-assertions-core-jvm:4.6.3") // for kotest core jvm assertions
	testImplementation("io.kotest:kotest-property-jvm:4.6.3") // for kotest property test
}

tasks.compileKotlin {

	kotlinOptions {

		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.test {

	useJUnitPlatform()
}

jacoco {

	toolVersion = "0.8.7"
}

tasks.jacocoTestReport {

	reports {

		xml.required.set(false)
		csv.required.set(false)
		html.outputLocation.set(file("${buildDir}/coverage"))
	}
}

tasks.getByName<Jar>("jar") {

	enabled = false
}
