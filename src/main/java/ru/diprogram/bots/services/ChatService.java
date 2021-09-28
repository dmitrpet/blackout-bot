package ru.diprogram.bots.services;

import org.springframework.stereotype.Service;
import ru.diprogram.bots.BotChannel;
import ru.diprogram.bots.entities.Chat;
import ru.diprogram.bots.entities.Dialog;
import ru.diprogram.bots.repositories.ChatRepository;

@Service
public class ChatService {
    private final ChatRepository chatRepository;

    ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public Chat findOrCreateById(String chatId, BotChannel botChannel) {
        Chat chat = chatRepository.findFirstByBotChannelAndChatId(botChannel, chatId);
        if (null == chat) {
            chat = Chat.createChat(botChannel, chatId);
        }
        return chat;
    }

    public void removeActiveDialog(Chat chat) {
        chat.setActiveDialog(null);
        chatRepository.save(chat);
    }

    public void createDialog(Chat chat, Dialog dialog) {
        chat.setActiveDialog(dialog);
        chatRepository.save(chat);
    }
}
