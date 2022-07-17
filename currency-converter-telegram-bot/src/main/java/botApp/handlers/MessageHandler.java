package botApp.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import botApp.enums.ButtonNameEnum;
import botApp.keyboards.ReplyKeyboardMaker;
import botApp.parsers.CurrencyRateParser;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class MessageHandler {

	//public static final Logger log = LoggerFactory.getLogger(MessageHandler.class);
	@Autowired
	private CurrencyRateParser currencyRateParser; // = new CurrencyRateParser();
	
	
	@Autowired
	private ReplyKeyboardMaker replyKeyboardMaker;// = new ReplyKeyboardMaker();
	
    public BotApiMethod<?> answerMessage(Message message) {
        String chatId = message.getChatId().toString();
        
        // TODO switch case
        String inputText = message.getText();
        if (inputText == null) {
        	throw new IllegalArgumentException();
        } else if (inputText.equals("/start")) {
        	return getStartMessage(chatId);
    	} else if (inputText.equals(ButtonNameEnum.CURRENCY_RATES.getButtonName())) {
    		return getCurrencyRatesList(chatId);
    	} else if (inputText.equals(ButtonNameEnum.CONVERT.getButtonName())) {
    		return new SendMessage(chatId, "Введите количество единиц валюты");
    	} else if (isNumber(inputText)) {
    		return new SendMessage(chatId, "Разобраться с последовательными вызовами");
    	} else {
			return getMistakeMessage(chatId);
		}
    }
    
    private SendMessage addMainMenuKeyboard(SendMessage sendMessage) {
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }

    private SendMessage getStartMessage(String chatId) {
        SendMessage sendMessage = new SendMessage(chatId, "start");
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        return sendMessage;
    }
    
    private SendMessage getCurrencyRatesList(String chatId) {
    	SendMessage sendMessage = new SendMessage(chatId, currencyRateParser.parseCurrencyRatesList());
    	return addMainMenuKeyboard(sendMessage);
    }
    
    private SendMessage getMistakeMessage(String chatId) {
    	SendMessage sendMessage = new SendMessage(chatId, "Неизвестная команда");
    	return addMainMenuKeyboard(sendMessage);
    }
    
    private boolean isNumber(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}