package pl.polsl.io.Mistboard.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.polsl.io.Mistboard.pojo.Game;
import pl.polsl.io.Mistboard.pojo.User;

import java.util.List;

public interface GameRepository extends PagingAndSortingRepository<Game, Integer> {
    List<Game> findByAuthor(User user, Pageable pageable);
    List<Game> findByAuthor(User user);
    List<Game> findAll();
    Game findById(int id);
}
