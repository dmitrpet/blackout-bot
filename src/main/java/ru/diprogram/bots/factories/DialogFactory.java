package ru.diprogram.bots.factories;

import org.springframework.stereotype.Service;
import ru.diprogram.bots.entities.AbstractMessage;
import ru.diprogram.bots.entities.Chat;
import ru.diprogram.bots.entities.Dialog;
import ru.diprogram.bots.entities.DialogType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DialogFactory {
    private final Map<DialogType, Dialog> dialogs;

    DialogFactory(List<Dialog> dialogList) {
        this.dialogs = dialogList.stream().collect(Collectors.toMap(Dialog::getDialogType, Function.identity()));
    }

    public Dialog createDialog(Chat chat, AbstractMessage message) {
        DialogType dialogType = DialogType.findByCommand(message.getText());
        return dialogs.get(dialogType);
    }
}
