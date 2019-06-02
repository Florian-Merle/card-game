package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.Card;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    @GetMapping(value = "/cards")
    public ResponseEntity getAllCards() {
        return this.requestForwarder.forwardRequest(
                MicroServices.CARD,
                "cards",
                null,
                List.class
        );
    }

    @GetMapping(value = "/cards/getById/{id}")
    public ResponseEntity getCardById(@PathVariable String id) {
        return this.requestForwarder.forwardRequest(
                MicroServices.CARD,
                "/cards/getById/"+id,
                null,
                Card.class
        );
    }

    @GetMapping(value = "/cards/getByName/{name}")
    public ResponseEntity getByName(@PathVariable String name) {
        return this.requestForwarder.forwardRequest(
                MicroServices.CARD,
                "/cards/getByName/"+name,
                null,
                Card.class
        );
    }

}
