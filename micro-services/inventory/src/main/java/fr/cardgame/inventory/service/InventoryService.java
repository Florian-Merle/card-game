package fr.cardgame.inventory.service;

import fr.cardgame.inventory.dto.AddCardDto;
import fr.cardgame.inventory.dto.DeleteCardDto;
import fr.cardgame.inventory.dto.GetCardDto;
import fr.cardgame.inventory.factory.InventoryFactory;
import fr.cardgame.inventory.model.Inventory;
import fr.cardgame.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryFactory inventoryFactory;

    public Inventory addCardInventory(AddCardDto addCardDto) {
        Inventory inventory = this.inventoryFactory.createInventory(addCardDto);
        this.inventoryRepository.save(inventory);
        return inventory;
    }

    public void deleteCardInventory(DeleteCardDto deleteCardDto){
        this.inventoryRepository.delete(deleteCardDto.getId());
    }

    public Iterable<Inventory> getCardInventory(GetCardDto getCardDto){

        return this.inventoryRepository.findByIdUser(getCardDto.getIdUser());
    }
}
