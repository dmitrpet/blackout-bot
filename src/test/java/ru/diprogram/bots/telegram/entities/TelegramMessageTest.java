package ru.diprogram.bots.telegram.entities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.diprogram.bots.BotChannel;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TelegramMessageTest {

    @Test
    void ofTelegramUpdate() {
        Update mockUpdate = mock(Update.class);
        Message mockMessage = mock(Message.class);
        doReturn(true).when(mockUpdate).hasMessage();
        doReturn(mockMessage).when(mockUpdate).getMessage();
        doReturn("test message").when(mockMessage).getText();
        doReturn(1234567890L).when(mockMessage).getChatId();
        Integer date = 1629649869; // Unix time for "2021-08-22T21:31:09+05:00"
        ZonedDateTime expectedDateTime = ZonedDateTime.of(2021, 8, 22, 21, 31, 9, 0, ZoneId.systemDefault());
        doReturn(date).when(mockMessage).getDate();

        TelegramMessage actualMessage = TelegramMessage.ofTelegramUpdate(mockUpdate);

        assertNotNull(actualMessage);
        assertTrue(expectedDateTime.isEqual(actualMessage.getDateTime()));
        assertEquals(mockUpdate, actualMessage.getOriginalUpdate());
        assertEquals(BotChannel.TELEGRAM, actualMessage.getBotChannel());
        assertEquals("test message", actualMessage.getText());
        assertEquals("1234567890", actualMessage.getChatId());
    }
}