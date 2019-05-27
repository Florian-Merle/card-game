package fr.cardgame.shop.controller;

import fr.cardgame.shop.dto.AchatCardDTO;
import fr.cardgame.shop.dto.VenteCardDTO;
import fr.cardgame.shop.service.ShopService;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShopRestController {

	private final ShopService shopService;

    public ShopRestController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("/buy")
    private String buyCard(@RequestBody AchatCardDTO achatCardDTO) {

        shopService.buy(achatCardDTO.getUser(), achatCardDTO.getIdCard());

        return "";
    }

    @RequestMapping("/sell")
    private String sellCard(@RequestBody VenteCardDTO venteCardDTO) {

        shopService.sell(venteCardDTO.getUser(), venteCardDTO.getIdInventory());

        return "";
    }





}
