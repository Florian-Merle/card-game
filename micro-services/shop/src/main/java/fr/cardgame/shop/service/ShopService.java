package fr.cardgame.shop.service;

import fr.cardgame.card.client.CardApiClient;
import fr.cardgame.card.dto.CardDto;
import fr.cardgame.inventory.InventoryApiClient;
import fr.cardgame.inventory.InventoryDto;
import fr.cardgame.user.client.UserApiClient;
import fr.cardgame.user.dto.UpdateUserCashDto;
import fr.cardgame.user.dto.User;

@Service
public class ShopService {


    @Autowired
    private CardApiClient cardApiClient;

    @Autowired
    private InventoryApiClient inventoryApiClient;

    private UserApiClient userApiClient;

    public void buy(User user, String idCard) {

        CardDto card = cardApiClient.getCardById(idCard);

        if (user.getCash() >= card.getPrice()) {

            // TODO create invotory
            inventoryApiClient.addInventory(null ); // TODO

            UpdateUserCashDto updateUserCashDto = new UpdateUserCashDto();
            updateUserCashDto.setId(user.getId());
            updateUserCashDto.setCash(user.getCash() - card.getPrice());

        }


    }

    public void sell(User user, InventoryDto inventoryDto)
    {
        user.setCash(user.getCash() + 555); // TODO

        inventoryApiClient.deleteInventory(inventoryDto);


    }

}
