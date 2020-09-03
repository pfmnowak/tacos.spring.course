package pl.miknow.tacos.data;

import org.springframework.data.repository.CrudRepository;

import pl.miknow.tacos.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}