package ru.diprogram.bots;

import lombok.Getter;

@Getter
public class BotButton {
    private final String caption;

    public BotButton(String caption) {
        this.caption = caption;
    }
}
