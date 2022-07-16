package botApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

	public static void main(String args[]) {
		//CurrencyRateParser currencyRateParser = new CurrencyRateParser();
		//System.out.println(currencyRateParser.parseCurrencyRate("EURRUB"));
		//Assertions.assertNotNull(name.asText());
		//Double value = objectMapper.readValue(response.toString(), Double.class);
		SpringApplication.run(App.class, args);
	}
}
