package fr.cardgame.transactions.controller;

import fr.cardgame.card.model.Card;
import fr.cardgame.user.model.User;
import fr.cardgame.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionsService {

    @Autowired
    private UserRepository userRepository;

    public int buy(Card card, User user) {
        int price = card.getPrice();

        if (!user.getCards().contains(card)
                && user.getCash() - price > 0) {
            user.getCards().add(card);
            user.setCash(user.getCash() - price);
            userRepository.save(user);
        } else {
            System.out.println("Impossible d'acheter la carte");
            return -1;
        }
        return 0;
    }

    public void sell(Card card, User user) {
        user.getCards().remove(card);
        userRepository.save(user);

        user.setCash(user.getCash() + card.getPrice());
    }
}
