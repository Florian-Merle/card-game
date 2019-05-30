package fr.cardgame.user;

import fr.cardgame.config.MicroServices;
import fr.cardgame.dto.user.GetByEmailDto;
import fr.cardgame.dto.user.User;
import fr.cardgame.service.ApiConsumer;
import fr.cardgame.service.AuthenticationProxy;
import fr.cardgame.service.RequestForwarder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestForwarderUnitTests {

    @InjectMocks
    private RequestForwarder requestForwarder;

    @Mock
    private ApiConsumer apiConsumer;

    @Mock
    private AuthenticationProxy authenticationProxy;

    @Test
    public void forwardAuthenticatedRequestTestFail() {
        HttpMethod requestMethod = HttpMethod.POST;
        MicroServices microServices = MicroServices.USER;
        String uri = "test";
        GetByEmailDto dto = new GetByEmailDto();

        when(this.authenticationProxy.validateToken(dto)).thenReturn(null);

        assertEquals(
                HttpStatus.BAD_REQUEST,
                this.requestForwarder.forwardAuthenticatedRequest(
                        requestMethod,
                        microServices,
                        uri,
                        dto,
                        User.class
                ).getStatusCode()
        );
    }

    @Test
    public void forwardAuthenticatedRequestTestOk() {

        HttpMethod requestMethod = HttpMethod.POST;
        MicroServices microServices = MicroServices.USER;
        String uri = "test";
        GetByEmailDto dto = new GetByEmailDto();

        User user = new User();

        when(this.authenticationProxy.validateToken(dto)).thenReturn(user);

        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        when(this.apiConsumer.consume(anyObject(), anyObject(), anyObject(), anyObject())).thenReturn(responseEntity);

        assertEquals(
                HttpStatus.OK,
                this.requestForwarder.forwardAuthenticatedRequest(
                        requestMethod,
                        microServices,
                        uri,
                        dto,
                        User.class
                ).getStatusCode()
        );
    }
}
