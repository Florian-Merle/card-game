package fr.cardgame.shop.controller;

import fr.cardgame.shop.dto.TransactionCardDTO;
import fr.cardgame.shop.service.ShopService;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShopRestController {

	private final ShopService shopService;

    public ShopRestController(ShopService shopService) {
        this.shopService = shopService;
    }

    @RequestMapping("/buy")
    private String buyCards(@RequestBody TransactionCardDTO transactionCardDTO) {

        shopService.buy(transactionCardDTO.getUser(), transactionCardDTO.getIdCard());

        return "";
    }



}
