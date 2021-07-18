package ru.diprogram.blackoutbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DisconnectableObject {
    private final LocalDateTime beginDateTime;
    private final LocalDateTime endDateTime;
}
