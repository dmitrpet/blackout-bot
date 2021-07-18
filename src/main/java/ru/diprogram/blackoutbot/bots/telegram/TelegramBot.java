package ru.diprogram.blackoutbot.bots.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.diprogram.blackoutbot.bots.Bot;

@Component
class TelegramBot extends TelegramLongPollingBot implements Bot {
    private final String botUserName;
    private final String botToken;

    TelegramBot(
            @Value("${app.bots.telegram.bot-user-name}") String botUserName,
            @Value("${app.bots.telegram.bot-token}") String botToken
    ) {
        this.botUserName = botUserName;
        this.botToken = botToken;
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

    }
}
