package fr.cardgame.card.service;


import fr.cardgame.card.repository.CardRepository;
import fr.cardgame.card.model.Card;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        cardRepository.findAll().forEach(cards::add);
        return cards;
    }

    public Card findByNameContaining(String name) {
        return cardRepository.findByNameContaining(name);
    }

    public Card getCard(String id) {
        return cardRepository.findOne(Integer.valueOf(id));
    }

    public void addCard(Card card) {
        cardRepository.save(card);
    }

    public void updateCard(Card card) {
        cardRepository.save(card);

    }

    public void deleteCard(String id) {
        cardRepository.delete(Integer.valueOf(id));
    }

}
