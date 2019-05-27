package fr.cardgame.card.client;

import fr.cardgame.card.dto.CardDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Api client
 * Used to consume data from user micro-service api
 */
@Service
public class CardApiClient {

    private static String API_URL = "http://localhost:8080";

    // URI(s)
    private static String GET_CARD_BY_ID_URI = "/cards/getById/";

    /**
     * Consume user micro-service. Try to get a card by its id.
     *
     * @param id
     * @return
     */
    public CardDto getCardById(String id) {
        // construct api url
        String url = CardApiClient.API_URL + CardApiClient.GET_CARD_BY_ID_URI + id;

        // consume api
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, null, CardDto.class);
    }

}
