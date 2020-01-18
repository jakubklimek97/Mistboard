package pl.polsl.io.Mistboard.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.io.Mistboard.pojo.Password;

public interface PasswordRepository extends CrudRepository<Password, Integer> {
}
