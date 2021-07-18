package ru.diprogram.blackoutbot.bots.telegram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelegramBotTest {
    private final static String TEST_BOT_TOKEN = "TEST_BOT_TOKEN";
    private final static String TEST_BOT_USERNAME = "TEST_BOT_USERNAME";
    private TelegramBot telegramBot;

    @BeforeEach
    void setUp() {
        this.telegramBot = new TelegramBot(TEST_BOT_USERNAME, TEST_BOT_TOKEN);
    }

    @Test
    void getBotUsername() {
        assertEquals(TEST_BOT_USERNAME, telegramBot.getBotUsername());
    }

    @Test
    void getBotToken() {
        assertEquals(TEST_BOT_TOKEN, telegramBot.getBotToken());
    }

    @Test
    void onUpdateReceived() {
    }
}