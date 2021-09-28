package ru.diprogram.bots.telegram;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.diprogram.bots.MessageHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TelegramBotAdapterTest {
    private final static String TEST_BOT_TOKEN = "TEST_BOT_TOKEN";
    private final static String TEST_BOT_USERNAME = "TEST_BOT_USERNAME";

    @Mock
    private MessageHandler messageHandler;
    @Mock
    private TelegramBot telegramBot;

    private LongPollingTelegramBot telegramBotAdapter;

    @BeforeEach
    void setUp() {
        this.telegramBotAdapter = new LongPollingTelegramBot(
                TEST_BOT_USERNAME,
                TEST_BOT_TOKEN,
                messageHandler,
                telegramBot
        );
    }

    @Test
    void getBotUsername() {
        assertEquals(TEST_BOT_USERNAME, telegramBotAdapter.getBotUsername());
    }

    @Test
    void getBotToken() {
        assertEquals(TEST_BOT_TOKEN, telegramBotAdapter.getBotToken());
    }

    @Test
    void onUpdateReceived() {
        Update mockUpdate = mock(Update.class);
        Message mockMessage = mock(Message.class);
        doReturn(true).when(mockUpdate).hasMessage();
        doReturn(mockMessage).when(mockUpdate).getMessage();

        telegramBotAdapter.onUpdateReceived(mockUpdate);

        //TODO: Not implement yet
//        verifyNoMoreInteractions(messageHandler);
    }
}