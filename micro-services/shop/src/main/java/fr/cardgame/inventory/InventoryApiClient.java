package fr.cardgame.inventory;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryApiClient {

    private static String API_URL = "http://localhost:8083";

    // URI(s)
    private static String ADD_INVENTORY = "/addCardInventory";
    private static String DELETE_INVENTORY = "/deleteCardInventory";
    private static String GET_ONE_INVENTORY = "/getOneCardInventory";


    /**
     * Consume inventory micro-service. Try to add a card by its id.
     *
     * @param inventoryDto
     * @return
     */
    public boolean addInventory(InventoryDto inventoryDto) {
        // construct api url
    	boolean ret = true;
        String url = API_URL + ADD_INVENTORY;

        Map<String, Object> params = new HashMap<>();
        params.put("idUser", inventoryDto.getIdUser());
        params.put("name", inventoryDto.getName());
        params.put("attack", inventoryDto.getAttack());
        params.put("defence", inventoryDto.getDefence());
        params.put("description", inventoryDto.getDescription());
        params.put("energy", inventoryDto.getEnergy());
        params.put("family", inventoryDto.getFamily());
        params.put("hp", inventoryDto.getHp());
        params.put("img", inventoryDto.getImgUrl());
        params.put("price", inventoryDto.getPrice());


        // consume api
        try
        {
	        RestTemplate restTemplate = new RestTemplate();
	        restTemplate.postForObject(url, params, InventoryDto.class);
	    }
	    catch(Exception e)
	    {
	    	ret = false;
	    	e.printStackTrace();
	    }
        return ret;
    }

    public boolean deleteInventory(DeleteCardDto deleteCardDto) 
    {
    	boolean ret = true;
        // construct api url
        String url = API_URL + DELETE_INVENTORY;

        // consume api
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(deleteCardDto, httpHeaders);
        try
        {
        ResponseEntity<DeleteCardDto> responseEntiy = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, DeleteCardDto.class);
        }
        catch(Exception e)
        {
        	ret = false;
        }
        return ret;
      }
    
    

    public Inventory getCardInventory(GetOneCardDto getOneCardDto) {
        String url = API_URL + GET_ONE_INVENTORY;

        // consume api
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity<>(getOneCardDto, httpHeaders);

        ResponseEntity<Inventory> responseEntiy = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Inventory.class);
        return responseEntiy.getBody();
    }
}
