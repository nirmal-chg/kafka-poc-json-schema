package com.playpen.kafkaJsonDemo.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.kafka.config.TopicBuilder
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors
import javax.annotation.PostConstruct

@Configuration
class Application(private val environment: Environment?, private val applicationProperties: ApplicationProperties) {

    @Bean
    fun profile(): String = getProfile()

    @Bean
    fun topic(): String = applicationProperties.topicName

    @Bean
    fun consumerGroupId(): String = applicationProperties.consumerGroupId

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().apply {

        this.dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        this.registerModule(JavaTimeModule())
        this.registerModule(KotlinModule())
        this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    @PostConstruct
    fun afterApplicationInitialization() {

        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))

        if (profile() != "prod") {

            TopicBuilder.name(applicationProperties.topicName)
                        .partitions(applicationProperties.numberOfPartition)
                        .replicas(applicationProperties.numberOfReplica)
                        .build()
        }
    }

    private fun getProfile(): String {

        if (environment == null) {

            return "default"
        }

        val envs: Set<String> = Arrays.stream(environment.activeProfiles).map { it.lowercase() }.collect(Collectors.toSet())

        return when {

            envs.isEmpty()        -> "default"
            envs.contains("prod") -> "prod"
            else                  -> envs.stream().findFirst().orElse("default").trim().ifEmpty { "default" }
        }
    }
}

