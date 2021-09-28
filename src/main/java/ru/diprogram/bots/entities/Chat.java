package ru.diprogram.bots.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.diprogram.bots.BotChannel;

@Getter
@Document(collection = "chats")
public class Chat {
    @Id
    private String chatId;
    @Indexed(direction = IndexDirection.ASCENDING, name = "botChannelIndex")
    private BotChannel botChannel;
    @Setter
    private Dialog activeDialog;

    @Transient
    public boolean hasActiveDialog() {
        return null != activeDialog;
    }

    public static Chat createChat(BotChannel botChannel, String chatId) {
        Chat chat = new Chat();
        chat.botChannel = botChannel;
        chat.chatId = chatId;
        return chat;
    }
}
