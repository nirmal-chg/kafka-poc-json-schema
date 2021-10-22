package com.playpen.kafkaJsonDemo.configuration

import com.playpen.kafkaJsonDemo.entity.Telemetry
import com.playpen.kafkaJsonDemo.publisher.TelemetryPublisher
import com.playpen.kafkaJsonDemo.util.DateUtil
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import java.util.*

@Configuration
@EnableScheduling
@Profile("!test")
class Scheduler(private val telemetryPublisher: TelemetryPublisher) {

    companion object {

        private val speedRandom = Random()
        private val clientRandom = Random()
    }

    @Scheduled(fixedRate = 1000L)
    fun publishMessage() {

        telemetryPublisher.publish(
            Telemetry(
                UUID.randomUUID(),
                clientRandom.nextInt(10 - 5) + 5,
                speedRandom.nextInt(100 - 5) + 5,
                DateUtil.nowInUTC()
            )
        )
    }
}
