package pl.miknow.tacos.data;

import org.springframework.data.repository.CrudRepository;
import pl.miknow.tacos.Model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
