package ru.diprogram.bots.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.diprogram.bots.MessageHandler;
import ru.diprogram.bots.telegram.entities.TelegramMessage;

@Service
class LongPollingTelegramBot extends TelegramLongPollingBot {
    private final String botUserName;
    private final String botToken;
    private final MessageHandler messageHandler;
    private final TelegramBot telegramBot;

    LongPollingTelegramBot(
            @Value("${app.bots.telegram.username}") String botUserName,
            @Value("${app.bots.telegram.token}") String botToken,
            MessageHandler messageHandler,
            TelegramBot telegramBot
    ) {
        this.botUserName = botUserName;
        this.botToken = botToken;
        this.messageHandler = messageHandler;
        this.telegramBot = telegramBot;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        messageHandler.handleMessage(telegramBot, TelegramMessage.ofTelegramUpdate(update));
    }
}
