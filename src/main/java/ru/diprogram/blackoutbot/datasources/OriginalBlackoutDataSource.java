package ru.diprogram.blackoutbot.datasources;

import ru.diprogram.blackoutbot.model.DisconnectableObject;

import java.util.List;
import java.util.UUID;

public interface OriginalBlackoutDataSource {
    List<DisconnectableObject> findDisconnectableObjectsByUuidAndObjectNum(UUID uuidStreet, String objNum);
}
