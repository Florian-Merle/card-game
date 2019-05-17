package fr.cardgame.user.factory;

import fr.cardgame.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    /**
     * Create a user from a UserRegisterInDTO and set its cash
     *
     * @param userRegisterInDTO
     * @return
     */
    /*
    public User createUser(UserRegisterInDTO userRegisterInDTO) {
        User user = new User(
                userRegisterInDTO.getFirstName(),
                userRegisterInDTO.getLastName(),
                userRegisterInDTO.getEmail(),
                userRegisterInDTO.getPassword(),
                User.DEFAULT_CASH
        );

        return user;
    }
    */
}
