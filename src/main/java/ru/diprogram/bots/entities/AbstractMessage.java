package ru.diprogram.bots.entities;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.diprogram.bots.BotChannel;

import java.time.ZonedDateTime;

@Setter
@Getter
@Document(collection = "messages")
public abstract class AbstractMessage {
    @Id
    private ObjectId id;
    private BotChannel botChannel;
    private String chatId;
    private ZonedDateTime dateTime;
    private String text;
}
