package fr.cardgame.inventory;

import fr.cardgame.card.client.CardApiClient;
import fr.cardgame.card.dto.CardDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryApiClient {

    private static String API_URL = "http://localhost:8080";

    // URI(s)
    private static String ADD_INVENTORY = ""; //TODO
    private static String DELETE_INVENTORY = ""; //TODO


    /**
     * Consume inventory micro-service. Try to add a card by its id.
     *
     * @param inventoryDto
     * @return
     */
    public void addInventory(InventoryDto inventoryDto) {
        // construct api url
        String url = this.API_URL + this.ADD_INVENTORY;

        Map<String, Object> params = new HashMap<>();
        params.put("inventory", inventoryDto);


        // consume api
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, InventoryDto.class);
    }

    public void deleteInventory(InventoryDto inventoryDto) {
        // construct api url
        String url = this.API_URL + this.DELETE_INVENTORY;

        Map<String, Object> params = new HashMap<>();
        params.put("inventory", inventoryDto);

        // consume api
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, InventoryDto.class);
    }
}
