package pl.polsl.io.Mistboard.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.io.Mistboard.pojo.Game;
import pl.polsl.io.Mistboard.pojo.User;

public interface GameRepository extends CrudRepository<Game, Integer> {
}
