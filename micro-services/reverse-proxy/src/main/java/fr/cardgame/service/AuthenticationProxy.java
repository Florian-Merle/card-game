package fr.cardgame.service;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.AuthenticatedGenericDto;
import fr.cardgame.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProxy {

    private static final String TOKEN_VALIDATION_URI = "getUser";

    @Autowired
    private ApiConsumer apiConsumer;

    /**
     * Validate user token, returns the user if the provided token is correct, null otherwise
     *
     * @param tokenDto
     * @return
     */
    public User validateToken(AuthenticatedGenericDto tokenDto) {
        ResponseEntity responseEntity = this.apiConsumer.consume(
                MicroServices.AUTHENTICATION.getUrl() + "/" + AuthenticationProxy.TOKEN_VALIDATION_URI,
                tokenDto.getTokenMap(),
                User.class
        );

        try {
            return (User) responseEntity.getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
