package ru.diprogram.bots.telegram;

import org.springframework.stereotype.Service;
import ru.diprogram.bots.AbstractBot;
import ru.diprogram.bots.BotButton;
import ru.diprogram.bots.BotChannel;
import ru.diprogram.bots.entities.Chat;

import java.util.List;

@Service
public class TelegramBot extends AbstractBot {
    TelegramBot() {
        super(BotChannel.TELEGRAM);
    }

    @Override
    public void sendMessage(Chat chat, String message) {

    }

    @Override
    public void sendMessageWithButtons(Chat chat, String message, List<BotButton> buttons) {

    }
}
