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

    @GetMapping("/cards")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/cards/getById/{id}")
    public Card getCardById(@PathVariable String id) {
        return cardService.getCard(id);
    }

    @GetMapping("/cards/getByName/{name}")
    public Card getCardByNameContaining(@PathVariable String name) {
        return cardService.findByNameContaining(name);
    }
}
