package ru.diprogram.bots.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.diprogram.bots.BotChannel;
import ru.diprogram.bots.entities.Chat;

public interface ChatRepository extends MongoRepository<Chat, ObjectId> {
    Chat findFirstByBotChannelAndChatId(BotChannel botChannel, String chatId);
}
