package fr.cardgame.user.factory;

import fr.cardgame.card.factory.CardFactory;
import fr.cardgame.user.dto.UserRegisterInDTO;
import fr.cardgame.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {

    @Autowired
    private CardFactory cardFactory;

    /**
     * Create a user from a UserRegisterInDTO and set its cash
     *
     * @param userRegisterInDTO
     * @return
     */
    public User createUser(UserRegisterInDTO userRegisterInDTO) {
        User user = new User(
                userRegisterInDTO.getFirstName(),
                userRegisterInDTO.getLastName(),
                userRegisterInDTO.getEmail(),
                userRegisterInDTO.getPassword(),
                User.DEFAULT_CASH
        );

        user.setCards(this.cardFactory.createRandomCardList());

        return user;
    }
}
