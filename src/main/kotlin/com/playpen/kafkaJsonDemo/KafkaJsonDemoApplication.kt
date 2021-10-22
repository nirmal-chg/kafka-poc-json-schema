package com.playpen.kafkaJsonDemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class KafkaJsonDemoApplication

fun main(args: Array<String>) {

	runApplication<KafkaJsonDemoApplication>(*args)
}
