package ru.diprogram.services;

import ru.diprogram.model.BotChannel;
import ru.diprogram.model.BotMessage;

public interface BotService {
    BotChannel getBotChannel();
    void sendMessage(String chatId, String message);
    BotMessage receiveMessage(Object sourceMessage);
}