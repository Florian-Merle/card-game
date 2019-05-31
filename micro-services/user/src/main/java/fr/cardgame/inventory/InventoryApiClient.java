package fr.cardgame.inventory;

import fr.cardgame.inventory.dto.AddCardDto;
import fr.cardgame.inventory.dto.Inventory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Api inventory
 * Used to consume data from inventory micro-service api
 */
@Service
public class InventoryApiClient {

    private static String API_URL = "http://localhost:8083";

    // URI(s)
    private static String ADD_CARD_INVENTORY = "/addCardInventory";

    /**
     * Consume user micro-service. Add a card to a user.
     *
     * @param addCardDto
     * @return
     */
    public Inventory create(AddCardDto addCardDto) {
        // construct api url
        String url = InventoryApiClient.API_URL + InventoryApiClient.ADD_CARD_INVENTORY;

        // consume api
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject(url, addCardDto, Inventory.class);
    }
}
