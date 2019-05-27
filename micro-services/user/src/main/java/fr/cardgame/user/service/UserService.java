package fr.cardgame.user.service;

import fr.cardgame.user.model.User;
import fr.cardgame.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Find a user by email
     *
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User getUserById(int id) {
        return this.userRepository.findOne(id);
    }

    public User update(User user) {
        return this.userRepository.save(user);
    }
}
