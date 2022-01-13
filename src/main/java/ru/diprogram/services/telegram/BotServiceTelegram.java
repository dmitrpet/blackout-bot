package ru.diprogram.services.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.diprogram.model.BotChannel;
import ru.diprogram.model.BotMessage;
import ru.diprogram.services.BotService;

@Slf4j
@Service
public class BotServiceTelegram extends TelegramLongPollingBot implements BotService, BeanPostProcessor {
    private final TelegramBotsApi telegramBotsApi;
    private final String botToken;
    private final String botUsername;

    public BotServiceTelegram(
            TelegramBotsApi telegramBotsApi,
            @Value("${app.bots.telegram.token}") String botToken,
            @Value("${app.bots.telegram.username}") String botUsername) {
        this.telegramBotsApi = telegramBotsApi;
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public BotChannel getBotChannel() {
        return BotChannel.TELEGRAM;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Can't send message [" + message + "] for chatId [" + chatId + "]", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BotMessage receiveMessage(Object sourceMessage) {
        Update update = (Update) sourceMessage;
        return () -> {
            if (update.hasMessage() && update.getMessage().hasText()) {
                return update.getMessage().getText();
            }
            return null;
        };
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            throw new BeanCreationException("Error register bot [" + beanName + "]", e);
        }
        return bean;
    }
}
