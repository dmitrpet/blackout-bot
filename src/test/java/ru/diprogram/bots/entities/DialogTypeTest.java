package ru.diprogram.bots.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DialogTypeTest {

    @Test
    void render() {
        String expected = "1. Найти отключения\n2. Подписаться на отключения";

        assertEquals(expected, DialogType.render());
    }
}