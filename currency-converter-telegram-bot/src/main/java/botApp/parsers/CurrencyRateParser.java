package botApp.parsers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CurrencyRateParser {
	
	public Double parseCurrencyRate(String pairs) {
		RestTemplate restTemplate = new RestTemplate();
		String resourseURL = "https://currate.ru/api/?get=rates&pairs=" + pairs + "&key=9ef013a123ae373bb7b466c17ecb02af";
		ResponseEntity<String> response = restTemplate.getForEntity(resourseURL, String.class);
		//System.out.println(response);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(response.getBody());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Double value = root.path("data").findValue(pairs).asDouble();
		return value;
	}
}
