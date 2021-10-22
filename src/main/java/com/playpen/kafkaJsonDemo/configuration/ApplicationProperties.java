package com.playpen.kafkaJsonDemo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ConfigurationProperties("poc.application")
@ConstructorBinding
@Validated
public class ApplicationProperties {

    @NotNull(message = "Topic name cannot be null.")
    @NotBlank(message = "Topic name cannot be blank.")
    private final String topicName;

    @NotNull(message = "Partition value cannot be null.")
    @Min(message = "Partition value must be greater than 0", value = 1)
    private final Integer numberOfPartition;

    @NotNull(message = "Number of replica cannot be null.")
    @Min(message = "Number of replica must be greater than 0", value = 1)
    private final Integer numberOfReplica;

    @NotNull(message = "Consumer group id cannot be null.")
    @NotBlank(message = "Consumer group id cannot be blank.")
    private final String consumerGroupId;

    public ApplicationProperties(String topicName, Integer numberOfPartition, Integer numberOfReplica, String consumerGroupId) {

        this.topicName = topicName;
        this.numberOfPartition = numberOfPartition;
        this.numberOfReplica = numberOfReplica;
        this.consumerGroupId = consumerGroupId;
    }

    public String getTopicName() {

        return topicName;
    }

    public Integer getNumberOfPartition() {

        return numberOfPartition;
    }

    public Integer getNumberOfReplica() {

        return numberOfReplica;
    }

    public String getConsumerGroupId() {

        return consumerGroupId;
    }
}
