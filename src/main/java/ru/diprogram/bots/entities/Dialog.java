package ru.diprogram.bots.entities;

import ru.diprogram.bots.Bot;

public interface Dialog {
    DialogType getDialogType();
    void start(Bot bot);
    void greetUser(Bot bot);
    void processMessage(AbstractMessage message, Bot bot);
    boolean isClosed();
}
