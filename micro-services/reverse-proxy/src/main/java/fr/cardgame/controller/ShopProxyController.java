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
        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.SHOP,
                "buy",
                achatCardDTO,
                AchatCardDTO.class
        );
    }

    @PostMapping(value = "/sell")
    public ResponseEntity sell(@RequestBody VenteCardDTO venteCardDTO) {
        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.SHOP,
                "sell",
                venteCardDTO,
                VenteCardDTO.class
        );
    }
}
