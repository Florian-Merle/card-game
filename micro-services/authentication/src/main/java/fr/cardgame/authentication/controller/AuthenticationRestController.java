package fr.cardgame.authentication.controller;

import fr.cardgame.authentication.dto.TokenRequestDto;
import fr.cardgame.authentication.dto.TokenResponseDto;
import fr.cardgame.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationRestController {
    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, value = "/getAuthToken")
    public ResponseEntity<TokenResponseDto> getAuthToken(@RequestBody TokenRequestDto tokenRequestDto) {
        TokenResponseDto tokenResponseDto = this.authenticationService.getAuthToken(tokenRequestDto);

        if (null == tokenResponseDto) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TokenResponseDto>(tokenResponseDto, HttpStatus.OK);
    }
}
