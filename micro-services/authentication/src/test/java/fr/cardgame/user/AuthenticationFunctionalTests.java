package fr.cardgame.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.cardgame.authentication.controller.AuthenticationRestController;
import fr.cardgame.authentication.dto.CredentialsDto;
import fr.cardgame.authentication.dto.TokenDto;
import fr.cardgame.authentication.service.AuthenticationService;
import fr.cardgame.user.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AuthenticationRestController.class, secure = false)
public class AuthenticationFunctionalTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    public void getAuthTokenBad() throws Exception {
        CredentialsDto credentialsDto = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(credentialsDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getAuthToken")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void getAuthTokenOk() throws Exception {
        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("1");
        when(this.authenticationService.getAuthToken(anyObject())).thenReturn(tokenDto);

        CredentialsDto credentialsDto = new CredentialsDto();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(credentialsDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getAuthToken")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(jsonPath("$.token").value("1"))
                .andDo(print());
    }


    @Test
    public void getUserBad() throws Exception {
        TokenDto tokenDto = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(tokenDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getUser")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void getUserOk() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("email");
        when(this.authenticationService.getUser(anyObject())).thenReturn(userDto);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken("1");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(tokenDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getUser")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(jsonPath("$.email").value("email"))
                .andDo(print());
    }
}
