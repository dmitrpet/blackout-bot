package ru.diprogram.bots;

import lombok.NonNull;
import ru.diprogram.bots.entities.Chat;
import ru.diprogram.bots.entities.DialogType;

import java.util.*;

public abstract class AbstractBot implements Bot {
    private final BotChannel botChannel;
    private final Set<String> dialogsPresented;

    public AbstractBot(@NonNull BotChannel botChannel) {
        this.botChannel = botChannel;
        this.dialogsPresented = new HashSet<>();
    }

    @Override
    public final BotChannel getBotChannel() {
        return botChannel;
    }

    @Override
    public final void presentDialogs(@NonNull Chat chat) {
        sendMessageWithButtons(chat, DialogType.render(), Arrays.asList(
                new BotButton("1"),
                new BotButton("2")
        ));
        dialogsPresented.add(chat.getChatId());
    }

    @Override
    public final void removeDialogsPresentedForChat(@NonNull Chat chat) {
        dialogsPresented.remove(chat.getChatId());
    }

    @Override
    public final boolean isDialogsPresented(@NonNull Chat chat) {
        return dialogsPresented.contains(chat.getChatId());
    }
}
