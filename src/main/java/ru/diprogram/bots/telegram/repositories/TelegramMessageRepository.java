package ru.diprogram.bots.telegram.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.diprogram.bots.telegram.entities.TelegramMessage;

public interface TelegramMessageRepository extends MongoRepository<TelegramMessage, ObjectId> {
}
