package pl.polsl.io.Mistboard.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.polsl.io.Mistboard.pojo.User;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    List<User> findByEmailIgnoreCase(String email);
    List<User> findAll(Sort sort);
}
