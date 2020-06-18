package pl.miknow.tacos.data;

import org.springframework.data.repository.CrudRepository;

import pl.miknow.tacos.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}