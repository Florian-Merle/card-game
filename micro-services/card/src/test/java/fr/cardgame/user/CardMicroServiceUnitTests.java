package fr.cardgame.user;

import fr.cardgame.card.factory.CardFactory;
import fr.cardgame.card.model.Card;
import fr.cardgame.card.repository.CardRepository;
import fr.cardgame.card.service.CardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardMicroServiceUnitTests {

    @Autowired
    CardService cardService;

    @Autowired
    CardFactory factory;

    @MockBean
    private CardRepository cardRepository;

    @Test
    public void testGetAll() {
        List<Card> cards = new ArrayList<>();
        when(cardRepository.findAll()).thenReturn(cards);
        assertEquals(cards, cardService.getAllCards());
    }

    @Test
    public void testFindByName() {
        Card card = new Card();
        when(cardRepository.findByNameContaining("Carapuce")).thenReturn(card);
        assertEquals(card, cardService.findByNameContaining("Carapuce"));
    }

    @Test
    public void testFindById() {
        Card card = new Card();
        when(cardRepository.findOne(1)).thenReturn(card);
        assertEquals(card, cardService.getCard("1"));
    }

    @Test
    public void testNotFound() {
        Card card = new Card();
        when(cardRepository.findOne(4)).thenReturn(card);
        assertNull(cardService.getCard("7"));
    }

    @Test
    public void testFactory() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            cards.add(new Card());

        when(cardRepository.findAll()).thenReturn(cards);
        assertEquals(5, factory.createRandomCardList().size());
        assertEquals(5, factory.createRandomCardList().stream().distinct().count());

    }
}
