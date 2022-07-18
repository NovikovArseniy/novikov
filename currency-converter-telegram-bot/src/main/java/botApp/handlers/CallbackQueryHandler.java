package botApp.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import botApp.data.CurrentDataRepository;
import botApp.parsers.CurrencyRateParser;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
public class CallbackQueryHandler {

	@Autowired
	private CurrentDataRepository currentDataRepository;
	
	@Autowired
	private CurrencyRateParser currencyRateParser;
	
	public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery){
        
		final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();
        String strForParser = data + "RUB";
        StringBuilder answerMessage = new StringBuilder();
        answerMessage.append(currentDataRepository.getValue(chatId))
        .append(" ")
        .append(data)
        .append(" = ")
        .append(currencyRateParser.convert(currentDataRepository.getValue(chatId), strForParser))
        .append(" RUB");
        currentDataRepository.deleteKey(chatId);
        return new SendMessage(chatId, answerMessage.toString());
	}
}
