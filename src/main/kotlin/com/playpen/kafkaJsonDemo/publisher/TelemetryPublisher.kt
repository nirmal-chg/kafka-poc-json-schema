package com.playpen.kafkaJsonDemo.publisher

import com.playpen.kafkaJsonDemo.entity.Telemetry
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class TelemetryPublisher(private val kafkaTemplate: KafkaTemplate<String, Telemetry>, private val topic: String) {

    private val logger = LoggerFactory.getLogger(TelemetryPublisher::class.java)

    fun publish(telemetry: Telemetry) {

        kafkaTemplate.send(topic, telemetry.id.toString(), telemetry).addCallback({

            val formattedDatetimeInUTCTimezone = if (it == null) "NOT FOUND" else Instant.ofEpochMilli(it.recordMetadata.timestamp()).toString()

            println("Record pushed at $formattedDatetimeInUTCTimezone with Id ${it?.producerRecord?.value()?.id}, client ID ${it?.producerRecord?.value()?.clientId} and speed ${it?.producerRecord?.value()?.speed}")

        }, {

            logger.error("Failed to publish record with ID ${telemetry.id} with Exception ${it.message}", it)
        })
    }
}
