package fr.cardgame.inventory.service;

import fr.cardgame.inventory.dto.AddCardDto;
import fr.cardgame.inventory.dto.DeleteCardDto;
import fr.cardgame.inventory.dto.GetCardDto;
import fr.cardgame.inventory.dto.UpdateCardDto;
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

    //ajouter une carte dans l'inventaire
    public Inventory addCardInventory(AddCardDto addCardDto) {
        Inventory inventory = this.inventoryFactory.createInventory(addCardDto);
        this.inventoryRepository.save(inventory);
        return inventory;
    }

    //supprimer une carte de l'inventaire
    public void deleteCardInventory(DeleteCardDto deleteCardDto){
        this.inventoryRepository.delete(deleteCardDto.getId());
    }

    //récupérer une carte de l'inventaire
    public Iterable<Inventory> getCardInventory(GetCardDto getCardDto){

        return this.inventoryRepository.findByIdUser(getCardDto.getIdUser());
    }

    //mettre à jour l'énergie d'une carte de l'inventaire
    public Inventory updateCardInventory(UpdateCardDto updateCardDto){
        Inventory inventory = this.inventoryRepository.findById(updateCardDto.getIdCard());
        inventory.setEnergy(updateCardDto.getEnergy());
        this.inventoryRepository.save(inventory);
        return inventory;
    }
}
