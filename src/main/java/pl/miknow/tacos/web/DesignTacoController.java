package pl.miknow.tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import pl.miknow.tacos.Ingredient;
import pl.miknow.tacos.Ingredient.Type;
import pl.miknow.tacos.Order;
import pl.miknow.tacos.Taco;
import pl.miknow.tacos.data.IngredientRepository;
import pl.miknow.tacos.data.TacoRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	private final IngredientRepository ingredientRepo;
	private TacoRepository designRepo;
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
		this.ingredientRepo = ingredientRepo;
		this.designRepo = designRepo;
	}
	
//	Rozdział 3 (JDBC)
	@GetMapping
	public String showDesignForm(Model model) {
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepo.findAll().forEach(i -> ingredients.add(i));
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		return "design";
	}
	
//	Rozdział 2
//	public String showDesignForm(Model model) {
//		List<Ingredient> ingredients = Arrays.asList(
//				new Ingredient("FLTO", "przenna", Type.WRAP),
//				new Ingredient("COTO", "kukurydziana", Type.WRAP),
//				new Ingredient("GRBF", "mielona wołowina", Type.PROTEIN),
//				new Ingredient("CARN", "kawałki mięsa", Type.PROTEIN),
//				new Ingredient("TMTO", "pomidory pokrojone w kostkę", Type.VEGGIES),
//				new Ingredient("LETC", "sałata", Type.VEGGIES),
//				new Ingredient("CHED", "cheddar", Type.CHEESE),
//				new Ingredient("JACK", "Monterey Jack", Type.CHEESE),
//				new Ingredient("SLSA", "pikantny sos pomidorowy", Type.SAUCE),
//				new Ingredient("SRCR", "śmietana", Type.SAUCE)
//				);
//		Type[] types = Ingredient.Type.values();
//		for (Type type : types) {
//			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//		}
//		model.addAttribute("design", new Taco());
//		return "design";
//	}
	
	
//	Rozdział 3 (JDBC)
	
	@ModelAttribute(name="order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name="taco")
	public Taco taco() {
		return new Taco();
	}
	
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors, @ModelAttribute Order order) {
		if (errors.hasErrors()) {
			return "design";
		}
		Taco saved = designRepo.save(design);
		order.addDesign(saved);
//		log.info("Przetwarzanie projektu taco: " + design);
		return "redirect:/orders/current";
	}
	
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

	    return ingredients.stream()
	            .filter(x -> x.getType().equals(type))
	            .collect(Collectors.toList());

	}
	
}
