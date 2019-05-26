package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.user.GetByEmailDto;
import fr.cardgame.dto.user.User;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    @RequestMapping(method = RequestMethod.POST, value = "/getUserByEmail")
    private ResponseEntity getUserByEmail(@RequestBody GetByEmailDto getByEmailDto) {
        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.USER,
                "getUserByEmail",
                getByEmailDto,
                User.class
        );
    }
}
