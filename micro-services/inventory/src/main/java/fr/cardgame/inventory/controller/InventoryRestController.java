package fr.cardgame.inventory.controller;

import fr.cardgame.inventory.dto.AddCardDto;
import fr.cardgame.inventory.dto.DeleteCardDto;
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

	@RequestMapping(method = RequestMethod.POST, value = "/addCardInventory")
	private ResponseEntity addCardInventory(@RequestBody AddCardDto addCardDto){
		Inventory inventory = this.inventoryService.addCardInventory(addCardDto);

		return new ResponseEntity<Inventory>(inventory, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteCardInventory")
	private ResponseEntity deleteInventoryCard(@RequestBody DeleteCardDto deleteCardDto){
		this.inventoryService.deleteCardInventory(deleteCardDto);
		return new ResponseEntity(HttpStatus.OK);
	}


	/*@RequestMapping(method = RequestMethod.POST, value = "/getListCard")
	private String getInventory(@RequestBody GetByEmailDto getByEmailDto) {
		
		/*User user = this.inventoryServiceService.getUserByEmail(getByEmailDto.getEmail());

		if (null == user) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}*/
}
