package ru.diprogram.scripts.blackoutbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DisconnectedObject {
    private final String equipmentName;
    private final LocalDateTime beginDateTime;
    private final LocalDateTime endDateTime;
}
