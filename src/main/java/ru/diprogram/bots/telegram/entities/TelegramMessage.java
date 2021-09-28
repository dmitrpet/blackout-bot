package ru.diprogram.bots.telegram.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.diprogram.bots.BotChannel;
import ru.diprogram.bots.entities.AbstractMessage;
import ru.diprogram.bots.exceptions.BotRuntimeException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Setter
@Getter
@TypeAlias("telegram")
@Document(collection = "messages")
public class TelegramMessage extends AbstractMessage {
    private Update originalUpdate;

    public TelegramMessage() {
        this.setBotChannel(BotChannel.TELEGRAM);
    }

    public static TelegramMessage ofTelegramUpdate(Update update) {
        if (!update.hasMessage()) {
            throw new BotRuntimeException("Во входящем update от телеграм отсутствует message");
        }

        TelegramMessage telegramMessage = new TelegramMessage();
        telegramMessage.originalUpdate = update;
        Message msg = update.getMessage();
        telegramMessage.setChatId(msg.getChatId().toString());
        telegramMessage.setText(msg.getText());
        try {
            telegramMessage.setDateTime(
                    ZonedDateTime.ofInstant(
                            new Date(update.getMessage().getDate() * 1000L).toInstant(),
                            ZoneId.systemDefault()
                    )
            );
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
        return telegramMessage;
    }
}
