package fr.cardgame.user.repository;

import org.springframework.data.repository.CrudRepository;
import fr.cardgame.user.model.User;

/**
 * interface UserRepository
 *
 * this interface is used by spring boot and will give you an object implementing all the functions defined here
 *
 * to use it:
 * @Autowired
 * private UserRepository userRepository;
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * find a user by name
     *
     * @param email
     * @return
     */
    public User findByEmail(String email);
}
