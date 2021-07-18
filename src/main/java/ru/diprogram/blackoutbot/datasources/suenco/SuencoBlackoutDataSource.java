package ru.diprogram.blackoutbot.datasources.suenco;

import org.springframework.stereotype.Component;
import ru.diprogram.blackoutbot.datasources.OriginalBlackoutDataSource;
import ru.diprogram.blackoutbot.model.DisconnectableObject;

import java.util.List;
import java.util.UUID;

@Component
public class SuencoBlackoutDataSource implements OriginalBlackoutDataSource {
    @Override
    public List<DisconnectableObject> findDisconnectableObjectsByUuidAndObjectNum(UUID uuidStreet, String objNum) {
        return null;
    }
}
