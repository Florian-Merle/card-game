package fr.cardgame.authentication.controller;

import fr.cardgame.authentication.dto.CredentialsDto;
import fr.cardgame.authentication.dto.TokenDto;
import fr.cardgame.authentication.service.AuthenticationService;
import fr.cardgame.user.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationRestController {
    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Deliver a token
     *
     * @param credentialsDto
     * @return
     */
    @PostMapping(value = "/getAuthToken")
    public ResponseEntity<TokenDto> getAuthToken(@RequestBody CredentialsDto credentialsDto) {
        TokenDto tokenDto = this.authenticationService.getAuthToken(credentialsDto);

        if (null == tokenDto) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }

    /**
     * Make sure a token is valid, return the user data
     *
     * @param tokenDto
     * @return
     */
    @PostMapping(value = "/getUser")
    public ResponseEntity<UserDto> getUser(@RequestBody TokenDto tokenDto) {
        UserDto userDto = this.authenticationService.getUser(tokenDto);

        if (null == userDto) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
