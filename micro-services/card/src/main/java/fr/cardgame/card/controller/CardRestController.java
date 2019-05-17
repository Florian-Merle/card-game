package fr.cardgame.card.controller;

import fr.cardgame.card.model.Card;
import fr.cardgame.card.service.CardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardRestController {

    private final CardService cardService;

    public CardRestController(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("/cards")
    private List<Card> getAllCards() {
        return cardService.getAllCards();

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
