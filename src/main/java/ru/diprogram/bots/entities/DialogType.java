package ru.diprogram.bots.entities;

import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

@Getter
public enum DialogType {
    SHOW_BLACKOUTS("/find", "Найти отключения", 1),
    SUBSCRIBE("/subscribe", "Подписаться на отключения", 2);

    private final String commandName;
    private final String name;
    private final int num;

    DialogType(String commandName, String name, int num) {
        this.commandName = commandName;
        this.name = name;
        this.num = num;
    }

    public static String render() {
        return Arrays.stream(DialogType.values())
                .map(dialogType -> String.format("%d. %s", dialogType.num, dialogType.name))
                .collect(Collectors.joining("\n"));
    }

    public static DialogType findByCommand(String commandName) {
        return Arrays.stream(DialogType.values())
                .filter(dialogType -> dialogType.commandName.equals(commandName))
                .findFirst()
                .orElse(null);
    }
}
