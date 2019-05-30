package fr.cardgame.user;

import fr.cardgame.authentication.dto.CredentialsDto;
import fr.cardgame.authentication.dto.TokenDto;
import fr.cardgame.authentication.service.AuthenticationService;
import fr.cardgame.user.client.UserApiClient;
import fr.cardgame.user.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceUnitTests {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserApiClient userApiClient;


    @Test
    public void getAuthTokenTestOkNotFound() {
        CredentialsDto credentialsDto = new CredentialsDto();
        credentialsDto.setEmail("email");


        when(this.userApiClient.getUserByEmail("email")).thenReturn(null);

        assertEquals(null, this.authenticationService.getAuthToken(credentialsDto));
    }


    @Test
    public void getAuthTokenTestWrongPassword() {
        CredentialsDto credentialsDto = new CredentialsDto();
        credentialsDto.setEmail("email");
        credentialsDto.setPassword("wrongPass");

        UserDto userDto = new UserDto();
        userDto.setPassword("pass");

        when(this.userApiClient.getUserByEmail("email")).thenReturn(userDto);

        assertEquals(null, this.authenticationService.getAuthToken(credentialsDto));
    }

    @Test
    public void getAuthTokenTestOk() {
        CredentialsDto credentialsDto = new CredentialsDto();
        credentialsDto.setEmail("email");
        credentialsDto.setPassword("pass");

        UserDto userDto = new UserDto();
        userDto.setPassword("pass");
        userDto.setId(1);

        when(this.userApiClient.getUserByEmail("email")).thenReturn(userDto);

        assertEquals("1", this.authenticationService.getAuthToken(credentialsDto).getToken());
    }

    @Test
    public void getUserTest() {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("1");

        UserDto userDto = new UserDto();

        when(this.userApiClient.getUserById(1)).thenReturn(userDto);

        assertEquals(userDto, this.authenticationService.getUser(tokenDto));
    }

}
