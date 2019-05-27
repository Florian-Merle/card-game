package fr.cardgame.inventory.factory;

import fr.cardgame.inventory.dto.AddCardDto;
import fr.cardgame.inventory.model.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryFactory {

    public Inventory createInventory(AddCardDto addCardDto){

        Inventory inventory = new Inventory();
        inventory.setName(addCardDto.getName());
        inventory.setDescription(addCardDto.getDescription());
        inventory.setFamily(addCardDto.getFamily());
        inventory.setHp(addCardDto.getHp());
        inventory.setEnergy(addCardDto.getEnergy());
        inventory.setDefence(addCardDto.getDefence());
        inventory.setAttack(addCardDto.getAttack());
        inventory.setPrice(addCardDto.getPrice());
        inventory.setImgUrl(addCardDto.getImgUrl());
        inventory.setIdUser(addCardDto.getIdUser());

        return inventory;
    }
}


