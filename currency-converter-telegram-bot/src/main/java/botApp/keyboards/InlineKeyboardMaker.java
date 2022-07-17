package botApp.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;


@Component
public class InlineKeyboardMaker {
	
	public InlineKeyboardMarkup getInlineMessageButtonsOfCurrencies() {
		InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
		return inlineKeyboardMarkup;
	}
}
