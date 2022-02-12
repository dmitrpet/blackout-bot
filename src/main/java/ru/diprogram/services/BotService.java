package ru.diprogram.services;

import ru.diprogram.model.BotChannel;
import ru.diprogram.model.BotMessage;
import ru.diprogram.services.exceptions.IncompatibleSourceMessage;

public interface BotService {
    BotChannel getBotChannel();
    void sendMessage(String chatId, String message);
    BotMessage receiveMessage(Object sourceMessage) throws IncompatibleSourceMessage;
}