package fr.cardgame.card.factory;

import fr.cardgame.card.controller.CardRepository;
import fr.cardgame.card.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CardFactory {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> createRandomCardList() {
        Iterable<Card> iterable = cardRepository.findAll();
        List<Card> list = new ArrayList<>();
        iterable.iterator().forEachRemaining(list::add);
        Collections.shuffle(list);
        list = list.subList(0, 5);

        return list;
    }
}
