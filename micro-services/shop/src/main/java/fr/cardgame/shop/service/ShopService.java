package fr.cardgame.shop.service;

import fr.cardgame.card.client.CardApiClient;
import fr.cardgame.card.dto.CardDto;
import fr.cardgame.inventory.DeleteCardDto;
import fr.cardgame.inventory.InventoryDto;
import fr.cardgame.inventory.InventoryApiClient;
import fr.cardgame.user.client.UserApiClient;
import fr.cardgame.user.dto.UpdateUserCashDto;
import fr.cardgame.user.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {


    @Autowired
    private CardApiClient cardApiClient;

    @Autowired
    private InventoryApiClient inventoryApiClient;

    @Autowired
    private UserApiClient userApiClient;

    public void buy(User user, String idCard) {

        CardDto card = cardApiClient.getCardById(idCard);

        if (user.getCash() >= card.getPrice()) {

            InventoryDto inventoryDto = new InventoryDto();
            inventoryDto.setAttack(card.getAttack());
            inventoryDto.setDefence(card.getDefence());
            inventoryDto.setDescription(card.getDescription());
            inventoryDto.setEnergy(card.getEnergy());
            inventoryDto.setFamily(card.getFamily());
            inventoryDto.setHp(card.getHp());
            inventoryDto.setIdUser(user.getId());
            inventoryDto.setImgUrl(card.getImgUrl());
            inventoryDto.setName(card.getName());
            inventoryDto.setPrice(card.getPrice());

            inventoryApiClient.addInventory(inventoryDto);

            UpdateUserCashDto updateUserCashDto = new UpdateUserCashDto();
            updateUserCashDto.setId(user.getId());
            updateUserCashDto.setCash(user.getCash() - card.getPrice());

            userApiClient.updateUserCash(updateUserCashDto);

        }


    }

    public void sell(User user, Integer idInventory) {

        UpdateUserCashDto updateUserCashDto = new UpdateUserCashDto();
        updateUserCashDto.setId(user.getId());
        updateUserCashDto.setCash(user.getCash() + 666); // TODO
        userApiClient.updateUserCash(updateUserCashDto);

        DeleteCardDto deleteCardDto = new DeleteCardDto();
        deleteCardDto.setId(idInventory);

        inventoryApiClient.deleteInventory(deleteCardDto);

    }

}
