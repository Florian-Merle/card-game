package fr.cardgame.card;

import fr.cardgame.card.dto.Card;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
/**
 * Api card
 * Used to consume data from card micro-service api
 */

public class CardApiClient {

    private static String API_URL = "http://localhost:8080";

    // URI(s)
    private static String GET_CARDS = "/cards";

    /**
     * Consume user micro-service. Get card list.
     *
     * @return
     */
    public List<Card> getCardList() {
        // construct api url
        String url = CardApiClient.API_URL + CardApiClient.GET_CARDS;

        // consume api
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Card>>(){}).getBody();
    }
}
