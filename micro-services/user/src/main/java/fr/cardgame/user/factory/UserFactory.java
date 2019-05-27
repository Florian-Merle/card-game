package fr.cardgame.user.factory;

import fr.cardgame.user.dto.UserRegisterDto;
import fr.cardgame.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    /**
     * Create a user from a UserRegisterInDTO and set its cash
     *
     * @param userRegisterDto
     * @return
     */
    public User createUser(UserRegisterDto userRegisterDto) {
        User user = new User(
                userRegisterDto.getFirstName(),
                userRegisterDto.getLastName(),
                userRegisterDto.getEmail(),
                userRegisterDto.getPassword(),
                User.DEFAULT_CASH
        );

        return user;
    }
}
