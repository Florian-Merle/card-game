package fr.cardgame.service;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.AuthenticatedGenericDto;
import fr.cardgame.dto.GenericDto;
import fr.cardgame.dto.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequestForwarder {

    @Autowired
    private ApiConsumer apiConsumer;

    @Autowired
    private AuthenticationProxy authenticationProxy;

    /**
     * Authenticate a user and the forward its request to the correct micro service
     *
     * @param microServices
     * @param dto
     * @return
     */
    public <T> ResponseEntity forwardAuthenticatedRequest(MicroServices microServices, String uri, AuthenticatedGenericDto dto, Class<T> classType) {
        // user not authenticated
        User user;
        if (null == (user = this.authenticationProxy.validateToken(dto))) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        dto.setUser(user);

        return this.forwardRequest(microServices, uri, dto, classType);
    }

    /**
     * Forward some request to the correct micro service
     *
     * @param microServices
     * @param dto
     * @return
     */
    public <T> ResponseEntity forwardRequest(MicroServices microServices, String uri, GenericDto dto, Class<T> classType) {
        return this.apiConsumer.consume(
                microServices.getUrl() + "/" + uri,
                dto.toMap(),
                classType
        );
    }
}
