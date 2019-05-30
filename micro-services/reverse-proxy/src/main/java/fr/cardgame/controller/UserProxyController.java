package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.user.GetByEmailDto;
import fr.cardgame.dto.user.GetByIdDto;
import fr.cardgame.dto.user.User;
import fr.cardgame.dto.user.UserRegisterDto;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    @PostMapping(value = "/getUserByEmail")
    public ResponseEntity getUserByEmail(@RequestBody GetByEmailDto getByEmailDto) {
        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.USER,
                "getUserByEmail",
                getByEmailDto,
                User.class
        );
    }

    @PostMapping(value = "/getUserById")
    public ResponseEntity getUserByEmail(@RequestBody GetByIdDto getByIdDto) {
        return this.requestForwarder.forwardAuthenticatedRequest(
                MicroServices.USER,
                "getUserById",
                getByIdDto,
                User.class
        );
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody UserRegisterDto userRegisterDto) {
        return this.requestForwarder.forwardRequest(
                MicroServices.USER,
                "register",
                userRegisterDto,
                User.class
        );
    }
}
