package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.inventory.*;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InventoryProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    //ajouter une carte à l'inventaire
    @PostMapping(value = "/addCardInventory")
    public ResponseEntity getUserByEmail(@RequestBody AddCardDto addCardDto) {
        System.out.println("proxy: /addCardInventory");

        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.INVENTORY,
                "addCardInventory",
                addCardDto,
                Inventory.class
        );
    }

    //supprimer une carte de l'inventaire
    @DeleteMapping(value = "/deleteCardInventory")
    public ResponseEntity deleteInventoryCard(@RequestBody DeleteCardDto deleteCardDto) {
        System.out.println("proxy: /deleteCardInventory");

        return this.requestForwarder.forwardAuthenticatedRequest(
                HttpMethod.DELETE,
                MicroServices.INVENTORY,
                "deleteCardInventory",
                deleteCardDto,
                ResponseEntity.class
        );
    }

    //récupérer une carte de l'inventaire
    @PostMapping(value = "/getCardInventory")
    public ResponseEntity getInventoryCards(@RequestBody GetCardDto getCardDto) {
        System.out.println("proxy: /getCardInventory");

        return this.requestForwarder.forwardAuthenticatedRequest(
                HttpMethod.POST,
                MicroServices.INVENTORY,
                "getCardInventory",
                getCardDto,
                Iterable.class
        );
    }

    //récupérer une carte de l'inventaire
    @RequestMapping(method = RequestMethod.POST, value = "/getOneCardInventory")
    public ResponseEntity getOneInventoryCard(@RequestBody GetOneCardDto getOneCardDto) {
        System.out.println("proxy: /getOneCardInventory");

        return this.requestForwarder.forwardAuthenticatedRequest(
                HttpMethod.POST,
                MicroServices.INVENTORY,
                "getOneCardInventory",
                getOneCardDto,
                Inventory.class
        );
    }

    //mettre à jour l'energie d'une carte de l'inventaire
    @PutMapping(value = "/updateCardInventory")
    public ResponseEntity updateInventoryCard(@RequestBody UpdateCardDto updateCardDto){
        System.out.println("proxy: /updateCardInventory");

        return this.requestForwarder.forwardAuthenticatedRequest(
                HttpMethod.PUT,
                MicroServices.INVENTORY,
                "updateCardInventory",
                updateCardDto,
                Inventory.class
        );
    }
}
