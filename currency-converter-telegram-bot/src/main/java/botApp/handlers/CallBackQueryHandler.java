package botApp.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

//@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CallBackQueryHandler {

	public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery){
        
		final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();
        
        return new SendMessage(chatId, "Не сделано");
	}
}
