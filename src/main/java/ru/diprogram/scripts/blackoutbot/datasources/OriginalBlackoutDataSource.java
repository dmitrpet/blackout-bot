package ru.diprogram.scripts.blackoutbot.datasources;

import ru.diprogram.scripts.blackoutbot.model.DisconnectedObject;

import java.util.List;
import java.util.UUID;

public interface OriginalBlackoutDataSource {
    List<DisconnectedObject> findDisconnectedObjectsByUuidAndObjectNum(UUID fiasId);
}
