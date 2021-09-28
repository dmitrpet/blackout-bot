package ru.diprogram.bots.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diprogram.bots.BotChannel;
import ru.diprogram.bots.entities.Chat;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class ChatRepositoryTest {
    @Autowired
    private ChatRepository chatRepository;

    @BeforeEach
    void setUp() {
        Chat chat = new Chat();
        chatRepository.save(chat);

        chat = new Chat();
        chatRepository.save(chat);
    }

    @Test
    void findFirstByBotChannelAndChatId() {
        Chat chat = chatRepository.findFirstByBotChannelAndChatId(BotChannel.TELEGRAM, "12345");
//TODO: Not Implement yet
    }
}