package ru.diprogram.bots;

import ru.diprogram.bots.entities.Chat;

import java.util.List;

public interface Bot {
    BotChannel getBotChannel();
    void sendMessage(Chat chat, String message);
    void sendMessageWithButtons(Chat chat, String message, List<BotButton> buttons);
    void presentDialogs(Chat chat);
    void removeDialogsPresentedForChat(Chat chat);
    boolean isDialogsPresented(Chat chat);
}
