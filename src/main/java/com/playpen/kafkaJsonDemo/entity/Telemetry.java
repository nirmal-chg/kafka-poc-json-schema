package com.playpen.kafkaJsonDemo.entity;

import com.fasterxml.jackson.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "clientId", "speed", "createdAt"})
public class Telemetry {

    @JsonProperty("id")
    private final UUID id;

    @JsonProperty("clientId")
    private final int clientId;

    @JsonProperty("speed")
    private final int speed;

    @JsonProperty("createdAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="UTC")
    private final LocalDateTime createdAt;

    @JsonCreator
    public Telemetry(@NotNull @JsonProperty("id") UUID id,
                     @NotNull @JsonProperty("clientId") int clientId,
                     @NotNull @JsonProperty("speed") int speed,
                     @NotNull @JsonProperty("createdAt") LocalDateTime createdAt) {

        this.id = id;
        this.clientId = clientId;
        this.speed = speed;
        this.createdAt = createdAt;
    }

    public UUID getId() {

        return id;
    }

    public int getClientId() {

        return clientId;
    }

    public int getSpeed() {

        return speed;
    }

    public LocalDateTime getCreatedAt() {

        return createdAt;
    }

    @Override
    public String toString() {

        return "Telemetry{" +
                "id='" + id + '\'' +
                ", clientId=" + clientId +
                ", speed=" + speed +
                ", createdAt=" + createdAt +
                '}';
    }
}
