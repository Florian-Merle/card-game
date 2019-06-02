package fr.cardgame.shop.controller;

import fr.cardgame.shop.dto.AchatCardDTO;
import fr.cardgame.shop.dto.VenteCardDTO;
import fr.cardgame.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopRestController {

    @Autowired
	private ShopService shopService;

    @PostMapping(value = "/buy")
    public String buyCard(@RequestBody AchatCardDTO achatCardDTO) {
        System.out.println("shop: /shop");

        return shopService.buy(achatCardDTO.getUser(), achatCardDTO.getIdCard());
    }

    @PostMapping(value = "/sell")
    public String sellCard(@RequestBody VenteCardDTO venteCardDTO) {
        System.out.println("shop: /sell");

        return shopService.sell(venteCardDTO.getUser(), venteCardDTO.getIdInventory());
    }
}
