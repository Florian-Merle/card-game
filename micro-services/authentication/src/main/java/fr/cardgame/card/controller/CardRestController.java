package fr.cardgame.card.controller;


import fr.cardgame.card.model.Card;
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

    @RequestMapping(method = RequestMethod.POST, value = "/cards")
    public void addCard(@RequestBody Card card) {
        cardService.addCard(card);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cards/{id}")
    public void updateCard(@RequestBody Card card, @PathVariable String id) {
        card.setId(Integer.valueOf(id));
        cardService.updateCard(card);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cards/{id}")
    public void deleteCard(@PathVariable String id) {
        cardService.deleteCard(id);
    }


}
