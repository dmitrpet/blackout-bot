package ru.diprogram.services.telegram;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import ru.diprogram.model.BotChannel;
import ru.diprogram.model.BotMessage;
import ru.diprogram.services.exceptions.IncompatibleSourceMessage;

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
    class receiveMessage {
        @Test
        void pass() throws IncompatibleSourceMessage {
            Message message = new Message();
            message.setText("expected text");
            Update update = new Update();
            update.setMessage(message);

            BotMessage actualBotMessage = botServiceTelegram.receiveMessage(update);

            assertThat(actualBotMessage).isNotNull();
            assertThat(actualBotMessage.getTextMessage()).isEqualTo("expected text");
        }

        @Test
        void throwIncompatibleSourceMessage() {
            IncompatibleSourceMessage actual = Assertions.assertThrows(
                    IncompatibleSourceMessage.class,
                    () -> botServiceTelegram.receiveMessage(new Object()));
            assertThat(actual).hasMessage("Expected org.telegram.telegrambots.meta.api.objects.Update but was java.lang.Object");
        }

        @Test
        void returnNullWhenMessageNotExistInUpdate() throws IncompatibleSourceMessage {
            Update update = new Update();

            BotMessage actualBotMessage = botServiceTelegram.receiveMessage(update);

            assertThat(actualBotMessage).isNotNull();
            assertThat(actualBotMessage.getTextMessage()).isNull();
        }
    }

    @Nested
    class afterPropertiesSet {
        @Test
        void ok() throws Exception {
            botServiceTelegram.afterPropertiesSet();

            verify(telegramBotsApi).registerBot(eq(botServiceTelegram));
        }

        @Test
        void telegramApiException() throws TelegramApiException {
            doThrow(TelegramApiException.class).when(telegramBotsApi).registerBot(any(LongPollingBot.class));

            Assertions.assertThrows(
                    TelegramApiException.class,
                    () -> botServiceTelegram.afterPropertiesSet());
            verify(telegramBotsApi).registerBot(eq(botServiceTelegram));
        }
    }
}