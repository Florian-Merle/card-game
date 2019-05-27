package fr.cardgame.inventory;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryApiClient {

    private static String API_URL = "http://localhost:8083";

    // URI(s)
    private static String ADD_INVENTORY = "/addCardInventory"; //TODO
    private static String DELETE_INVENTORY = "/deleteCardInventory"; //TODO


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
        params.put("idUser", inventoryDto.getIdUser());
        params.put("name", inventoryDto.getName());
        params.put("attack",inventoryDto.getAttack());
        params.put("defence",inventoryDto.getDefence());
        params.put("description",inventoryDto.getDescription());
        params.put("energy",inventoryDto.getEnergy());
        params.put("family",inventoryDto.getFamily());
        params.put("hp",inventoryDto.getHp());
        params.put("img",inventoryDto.getImgUrl());
        params.put("price",inventoryDto.getPrice());


        // consume api
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, InventoryDto.class);
    }

    public void deleteInventory(DeleteCardDto deleteCardDto) {
        // construct api url
        String url = this.API_URL + this.DELETE_INVENTORY;

        Map<String, Object> params = new HashMap<>();
        params.put("id",deleteCardDto.getId());

        // consume api
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, params, DeleteCardDto.class);
    }
}
