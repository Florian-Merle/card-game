package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.Card;
import fr.cardgame.dto.user.GetByEmailDto;
import fr.cardgame.dto.user.GetByIdDto;
import fr.cardgame.dto.user.User;
import fr.cardgame.dto.user.UserRegisterDto;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    @RequestMapping(method = RequestMethod.GET, value = "/cards")
    private ResponseEntity getAllCards() {
        return this.requestForwarder.forwardRequest(
                MicroServices.CARD,
                "cards",
                null,
                List.class
        );
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cards/getById/{id}")
    private ResponseEntity getCardById(@PathVariable String id) {
        return this.requestForwarder.forwardRequest(
                MicroServices.CARD,
                "/cards/getById/"+id,
                null,
                Card.class
        );
    }

}
