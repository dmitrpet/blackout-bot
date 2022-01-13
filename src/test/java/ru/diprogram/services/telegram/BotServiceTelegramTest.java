package ru.diprogram.services.telegram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.BeanCreationException;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import ru.diprogram.model.BotChannel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BotServiceTelegramTest {
    @Mock
    private TelegramBotsApi telegramBotsApi;

    private BotServiceTelegram botServiceTelegram;

    @BeforeEach
    void setUp() {
        botServiceTelegram = new BotServiceTelegram(
                telegramBotsApi,
                "token",
                "username"
        );
    }

    @Test
    void getBotChannel() {
        assertThat(botServiceTelegram.getBotChannel()).isEqualTo(BotChannel.TELEGRAM);
    }

    @Test
    void getBotUsername() {
        assertThat(botServiceTelegram.getBotUsername()).isEqualTo("username");
    }

    @Test
    void getBotToken() {
        assertThat(botServiceTelegram.getBotToken()).isEqualTo("token");
    }

    @Nested
    class postProcessAfterInitialization {
        @Test
        void ok() throws TelegramApiException {
            Object actualBean = botServiceTelegram.postProcessAfterInitialization(botServiceTelegram, "test");

            verify(telegramBotsApi).registerBot(eq(botServiceTelegram));
            assertThat(actualBean).isNotNull();
            assertThat(actualBean).isSameAs(botServiceTelegram);
        }

        @Test
        void telegramApiException() throws TelegramApiException {
            doThrow(TelegramApiException.class).when(telegramBotsApi).registerBot(any(LongPollingBot.class));

            BeanCreationException expected = Assertions.assertThrows(
                    BeanCreationException.class,
                    () -> botServiceTelegram.postProcessAfterInitialization(botServiceTelegram, "test"));
            assertThat(expected.getMessage()).isEqualTo("Error register bot [test]; nested exception is org.telegram.telegrambots.meta.exceptions.TelegramApiException");
            verify(telegramBotsApi).registerBot(eq(botServiceTelegram));
        }
    }
}