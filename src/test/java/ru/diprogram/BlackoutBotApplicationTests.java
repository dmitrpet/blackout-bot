package ru.diprogram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.diprogram.services.telegram.BotServiceTelegram;

import static org.mockito.Mockito.verify;

@SpringBootTest
class BlackoutBotApplicationTests {
	@MockBean
	private TelegramBotsApi telegramBotsApi;

	@Autowired
	private BotServiceTelegram botServiceTelegram;

	@Test
	void contextLoads() throws TelegramApiException {
		verify(telegramBotsApi).registerBot(botServiceTelegram);
	}
}
