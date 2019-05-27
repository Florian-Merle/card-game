package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.inventory.*;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    //ajouter une carte à l'inventaire
    @RequestMapping(method = RequestMethod.POST, value = "/addCardInventory")
    private ResponseEntity getUserByEmail(@RequestBody AddCardDto addCardDto) {
        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.INVENTORY,
                "addCardInventory",
                addCardDto,
                Inventory.class
        );
    }

    //supprimer une carte de l'inventaire
    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteCardInventory")
    private ResponseEntity deleteInventoryCard(@RequestBody DeleteCardDto deleteCardDto) {
        return this.requestForwarder.forwardAuthenticatedRequest(
                RequestMethod.DELETE,
                MicroServices.INVENTORY,
                "deleteCardInventory",
                deleteCardDto,
                ResponseEntity.class
        );
    }

    //récupérer une carte de l'inventaire
    @RequestMapping(method = RequestMethod.POST, value = "/getCardInventory")
    private ResponseEntity getInventoryCard(@RequestBody GetCardDto getCardDto) {
        return this.requestForwarder.forwardAuthenticatedRequest(
                RequestMethod.GET,
                MicroServices.INVENTORY,
                "getCardInventory",
                getCardDto,
                Iterable.class
        );
    }

    //mettre à jour l'energie d'une carte de l'inventaire
    @RequestMapping(method = RequestMethod.PUT, value = "/updateCardInventory")
    private ResponseEntity updateInventoryCard(@RequestBody UpdateCardDto updateCardDto){
        return this.requestForwarder.forwardAuthenticatedRequest(
                RequestMethod.PUT,
                MicroServices.INVENTORY,
                "updateCardInventory",
                updateCardDto,
                Inventory.class
        );
    }
}
