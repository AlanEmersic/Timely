package com.timely.timely.dto;


import java.time.Duration;
import java.time.LocalDateTime;

public record ProjectDto(Long id, String name, LocalDateTime startTime, LocalDateTime endTime, Duration duration) {
}
