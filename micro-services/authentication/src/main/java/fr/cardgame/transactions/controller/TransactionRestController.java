package fr.cardgame.transactions.controller;

import fr.cardgame.service.AuthenticationService;
import fr.cardgame.card.controller.CardService;
import fr.cardgame.card.model.Card;
import fr.cardgame.transactions.dto.BuyCardDTO;
import fr.cardgame.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionRestController {

    @Autowired
    private CardService cardService;

    @Autowired
    private AuthenticationService AuthService;

    @Autowired
    private TransactionsService transactionsService;

    @RequestMapping(method = RequestMethod.POST, value = "/buy")
    private String buyCard(@RequestBody BuyCardDTO cardId) {


        User user = AuthService.getLoggedInUser();
        if (user == null)
            return "Vous devez vous connecter";

        Card card = cardService.getCard(cardId.getCardId());

        if (card == null)
            return "La carte n'a pas été trouvé";

        if (transactionsService.buy(card, user) != 0) {
            return "Anomalie lors de l'achat";
        }

        return "Succès de la transaction !";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sell")
    private String sellCard(@RequestBody String cardId) {

        User user = AuthService.getLoggedInUser();
        if (user == null)
            return "Vous devez vous connecter";

        Card card = cardService.getCard(cardId);

        if (card == null)
            return "La carte n'a pas été trouvé";

        transactionsService.sell(card, user);

        return "Succès de la transaction !";
    }
}
