package fr.cardgame.card.controller;

import org.springframework.web.bind.annotation.*;

import fr.cpe.froissant.maxime.atelier2.card.model.Card;
import fr.cpe.froissant.maxime.atelier2.user.model.User;

import java.util.List;

@RestController
public class ShopRestController {

	private final ShopService shopService;

    public ShopRestController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("/buy")
    private String buyCards() {
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

    @RequestMapping("/cards/getById/{id}")
    private Card getCardById(@PathVariable String id) {
        return cardService.getCard(id);
    }

    @RequestMapping("/cards/getByName/{name}")
    private Card getCardByNameContaining(@PathVariable String name) {
        return cardService.findByNameContaining(name);
    }


}
