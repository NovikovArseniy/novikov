package botApp.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import botApp.parsers.CurrencyRateParser;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MessageHandler {

	CurrencyRateParser currencyRateParser = new CurrencyRateParser();
	
    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();

        String inputText = message.getText();
        //return new SendMessage(chatId, inputText);
        if (inputText == null) {
        	throw new IllegalArgumentException();
        } else {
        	return new SendMessage(chatId, currencyRateParser.parseCurrencyRate(inputText).toString());
        }
    }


}