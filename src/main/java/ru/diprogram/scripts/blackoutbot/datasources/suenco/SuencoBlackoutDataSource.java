package ru.diprogram.scripts.blackoutbot.datasources.suenco;

import org.springframework.stereotype.Component;
import ru.diprogram.scripts.blackoutbot.datasources.OriginalBlackoutDataSource;
import ru.diprogram.scripts.blackoutbot.model.DisconnectedObject;

import java.util.List;
import java.util.UUID;

@Component
public class SuencoBlackoutDataSource implements OriginalBlackoutDataSource {
    @Override
    public List<DisconnectedObject> findDisconnectedObjectsByUuidAndObjectNum(UUID fiasId) {
        return null;
    }
}
