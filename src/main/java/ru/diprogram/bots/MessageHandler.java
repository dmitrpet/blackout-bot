package ru.diprogram.bots;

import org.springframework.stereotype.Service;
import ru.diprogram.bots.entities.AbstractMessage;
import ru.diprogram.bots.entities.Chat;
import ru.diprogram.bots.entities.Dialog;
import ru.diprogram.bots.factories.DialogFactory;
import ru.diprogram.bots.services.ChatService;

@Service
public class MessageHandler {
    private final ChatService chatService;
    private final DialogFactory dialogFactory;

    MessageHandler(ChatService chatService, DialogFactory dialogFactory) {
        this.chatService = chatService;
        this.dialogFactory = dialogFactory;
    }

    public void handleMessage(Bot bot, AbstractMessage message) {
        Chat chat = chatService.findOrCreateById(message.getChatId(), message.getBotChannel());
        if (chat.hasActiveDialog()) {
            Dialog dialog = chat.getActiveDialog();
            if (dialog.isClosed()) {
                dialog.greetUser(bot);
                dialog.start(bot);
            } else {
                dialog.processMessage(message, bot);
                if (dialog.isClosed()) {
                    chatService.removeActiveDialog(chat);
                }
            }
        } else {
            if (!bot.isDialogsPresented(chat)) {
                bot.presentDialogs(chat);
            } else {
                Dialog dialog = dialogFactory.createDialog(chat, message);
                chatService.createDialog(chat, dialog);
            }
        }
    }
}
