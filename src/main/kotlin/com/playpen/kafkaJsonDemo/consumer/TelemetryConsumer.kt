package com.playpen.kafkaJsonDemo.consumer

import com.playpen.kafkaJsonDemo.entity.Telemetry
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class TelemetryConsumer {

//    @KafkaListener(topics = ["#{topic}"], groupId = "#{consumerGroupId}")
//    fun consumer(telemetry: Telemetry) {
//
//        println("Received $telemetry")
//    }
}
