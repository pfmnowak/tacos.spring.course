package pl.miknow.tacos.web;

import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pl.miknow.tacos.Model.Order;
import pl.miknow.tacos.Model.User;
import pl.miknow.tacos.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
//	public String orderForm(Model model) {
//		model.addAttribute("order", new Order());
	public String orderForm() {
		return "orderForm";
	}
	
	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}
		order.setUser(user);
		orderRepo.save(order);
		sessionStatus.setComplete();
//		log.info("Zamówienie zostało złożone: " + order);
		return "redirect:/";
	}
}