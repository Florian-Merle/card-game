package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.AchatCardDTO;
import fr.cardgame.dto.VenteCardDTO;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    @PostMapping(value = "/buy")
    public ResponseEntity buy(@RequestBody AchatCardDTO achatCardDTO) {
        System.out.println("proxy: /buy");

        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.SHOP,
                "buy",
                achatCardDTO,
                String.class
        );
    }

    @PostMapping(value = "/sell")
    public ResponseEntity sell(@RequestBody VenteCardDTO venteCardDTO) {
        System.out.println("proxy: /sell");

        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.SHOP,
                "sell",
                venteCardDTO,
                String.class
        );
    }
}
