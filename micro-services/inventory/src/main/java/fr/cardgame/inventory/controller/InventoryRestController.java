package fr.cardgame.inventory.controller;

import fr.cardgame.inventory.dto.*;
import fr.cardgame.inventory.model.Inventory;
import fr.cardgame.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryRestController {

	@Autowired
	private InventoryService inventoryService;

	//ajouter une carte à l'inventaire
	@RequestMapping(method = RequestMethod.POST, value = "/addCardInventory")
	private ResponseEntity addCardInventory(@RequestBody AddCardDto addCardDto){
		Inventory inventory = this.inventoryService.addCardInventory(addCardDto);

		return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
	}

	//supprimer une carte de l'inventaire
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteCardInventory")
	private ResponseEntity deleteInventoryCard(@RequestBody DeleteCardDto deleteCardDto){
		this.inventoryService.deleteCardInventory(deleteCardDto);
		return new ResponseEntity(HttpStatus.OK);
	}

	//récupérer les cartes de l'inventaire grace a l'id utilisateur
	@RequestMapping(method = RequestMethod.POST, value = "/getCardInventory")
	private ResponseEntity getInventoryCard(@RequestBody GetCardDto getCardDto){
		Iterable<Inventory> cardInventory = this.inventoryService.getCardInventory(getCardDto);
		return new ResponseEntity<Iterable<Inventory>>(cardInventory, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getOneCardInventory")
	private ResponseEntity getOneInventoryCard(@RequestBody GetOneCardDto getOneCardDto){
		Inventory card = this.inventoryService.getOneCardInventory(getOneCardDto);
		return new ResponseEntity(card,HttpStatus.OK);
	}

	//mettre à jour l'energie d'une carte de l'inventaire
	@RequestMapping(method = RequestMethod.PUT, value = "/updateCardInventory")
	private ResponseEntity updateInventoryCard(@RequestBody UpdateCardDto updateCardDto){
		Inventory inventory = this.inventoryService.updateCardInventory(updateCardDto);
		return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
	}
}
