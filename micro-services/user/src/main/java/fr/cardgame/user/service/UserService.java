package fr.cardgame.user.service;

import fr.cardgame.card.CardApiClient;
import fr.cardgame.card.dto.Card;
import fr.cardgame.inventory.InventoryApiClient;
import fr.cardgame.user.dto.UserRegisterDto;
import fr.cardgame.user.factory.AddCardDtoFactory;
import fr.cardgame.user.factory.UserFactory;
import fr.cardgame.user.model.User;
import fr.cardgame.user.repository.UserRepository;
import fr.cardgame.user.validator.UserRegisterDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRegisterDtoValidator userRegisterDtoValidator;

    @Autowired
    private UserFactory userFactory;

    @Autowired
    private CardApiClient cardApiClient;

    @Autowired
    private InventoryApiClient inventoryApiClient;

    @Autowired
    private AddCardDtoFactory addCardDtoFactory;

    /**
     * Find a user by email
     *
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    /**
     * Get a user by its id
     *
     * @param id
     * @return
     */
    public User getUserById(int id) {
        return this.userRepository.findOne(id);
    }

    public User update(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Method used to register a user
     *
     * @param userRegisterDto
     * @return
     */
    public User register(UserRegisterDto userRegisterDto) {
        // incorrect submitted data
        if (!this.userRegisterDtoValidator.validate(userRegisterDto)) {
            return null;
        }

        // user already exists
        User potentialUser = userRepository.findByEmail(userRegisterDto.getEmail());
        if (null != potentialUser) {
            return null;
        }

        // create the user and save it
        User user = this.userFactory.createUser(userRegisterDto);
        userRepository.save(user);

        // add 5 cards to the user
        List<Card> cardList = this.cardApiClient.getCardList();
        Collections.shuffle(cardList);
        cardList = cardList.subList(0, 5);

        // save cards
        for (Card card : cardList) {
            this.inventoryApiClient.create(
                    this.addCardDtoFactory.create(user, card)
            );
        }

        return user;
    }
}
