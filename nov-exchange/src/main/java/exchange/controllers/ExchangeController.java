package exchange.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import exchange.data.OrderRepository;
import exchange.entity.Currency;
import exchange.entity.ExchangeOrder;
import exchange.entity.ExchangeOrder.Action;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/index")
    public String index(){
        return "index";
    }

	//@Operation(summary = "Gets all users", tags = "user")
	@GetMapping("/show_orders")
	public List<ExchangeOrder> showOrders(){
		return orderRepository.orderList();
	}
	
	@PostMapping("/create_order")
	public @ResponseBody ExchangeOrder saveOrder(
			@RequestParam Currency currency,
			@RequestParam double volume,
			@RequestParam double priceInConventionalUnits,
			@RequestParam Action action) {
		ExchangeOrder order = new ExchangeOrder();
		order.setAction(action);
		order.setCurrency(currency);
		order.setVolume(volume);
		order.setPriceInConventionalUnits(priceInConventionalUnits);
		order = orderRepository.save(order);
		return order;
	}
	
	@GetMapping("/show_by_id")
	public ExchangeOrder showOne(@RequestParam long id) {
		return orderRepository.showOrderById(id);
	}
	
	@PostMapping("/delete")
	public String deleteOrderById(@RequestParam long id) {
		ExchangeOrder order = orderRepository.deleteOrderById(id);
		if (order == null) {
			return "Not existing id";
		} else {
			return "Deleted order id " + order.getId();
		}
	}
}
