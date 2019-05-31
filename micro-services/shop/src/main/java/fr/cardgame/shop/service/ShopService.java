package fr.cardgame.shop.service;

import fr.cardgame.card.client.CardApiClient;
import fr.cardgame.card.dto.CardDto;
import fr.cardgame.inventory.*;
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

    public String buy(User user, String idCard) {

        String resulat = "";
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
            return "Transaction OK";

        }

        return "Fond insuffisant";

    }

    public String sell(User user, Integer idInventory) {

        GetOneCardDto getOneCardDto = new GetOneCardDto();
        getOneCardDto.setId(idInventory);
        Inventory inve = inventoryApiClient.getCardInventory(getOneCardDto);

        UpdateUserCashDto updateUserCashDto = new UpdateUserCashDto();
        updateUserCashDto.setId(user.getId());
        updateUserCashDto.setCash(user.getCash() + inve.getPrice());
        userApiClient.updateUserCash(updateUserCashDto);

        DeleteCardDto deleteCardDto = new DeleteCardDto();
        deleteCardDto.setId(idInventory);

        inventoryApiClient.deleteInventory(deleteCardDto);

        return "Vente OK";
    }

}
