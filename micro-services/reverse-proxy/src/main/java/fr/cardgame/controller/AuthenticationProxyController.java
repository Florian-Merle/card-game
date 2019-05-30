package fr.cardgame.controller;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.authentication.CredentialsDto;
import fr.cardgame.dto.authentication.TokenDto;
import fr.cardgame.service.RequestForwarder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationProxyController {

    @Autowired
    private RequestForwarder requestForwarder;

    @PostMapping(value = "/getAuthToken")
    public ResponseEntity getAuthToken(@RequestBody CredentialsDto credentialsDto) {
        return this.requestForwarder.forwardRequest(
                MicroServices.AUTHENTICATION,
                "getAuthToken",
                credentialsDto,
                TokenDto.class
        );
    }
}
