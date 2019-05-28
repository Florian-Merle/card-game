package fr.cardgame.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.cardgame.user.controller.UserRestController;
import fr.cardgame.user.dto.GetByEmailDto;
import fr.cardgame.user.dto.GetByIdDto;
import fr.cardgame.user.dto.UserRegisterDto;
import fr.cardgame.user.model.User;
import fr.cardgame.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.Charset;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserRestController.class, secure = false)
public class UserFunctionalTests {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void getUserByEmail() throws Exception {
        User user = new User();
        user.setFirstName("a");
        user.setLastName("b");
        when(this.userService.getUserByEmail("user1@email.com")).thenReturn(user);

        GetByEmailDto getByEmailDto = new GetByEmailDto();
        getByEmailDto.setEmail("user1@email.com");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(getByEmailDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getUserByEmail")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(jsonPath("$.firstName").value("a"))
                .andExpect(jsonPath("$.lastName").value("b"))
                .andDo(print());
    }


    @Test
    public void getUserById() throws Exception {
        User user = new User();
        user.setFirstName("c");
        user.setLastName("d");
        when(this.userService.getUserById(2)).thenReturn(user);


        GetByIdDto getByIdDto = new GetByIdDto();
        getByIdDto.setId(2);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(getByIdDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getUserById")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(jsonPath("$.firstName").value("c"))
                .andExpect(jsonPath("$.lastName").value("d"))
                .andDo(print());
    }

    @Test
    public void registerOk() throws Exception {
        User user = new User();
        user.setEmail("test@test.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");

        when(this.userService.register(anyObject())).thenReturn(user);

        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("test@test.com");
        userRegisterDto.setFirstName("firstName");
        userRegisterDto.setLastName("lastName");
        userRegisterDto.setPassword("pass");
        userRegisterDto.setPasswordValidation("pass");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userRegisterDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.firstName").value("firstName"))
                .andExpect(jsonPath("$.lastName").value("lastName"))
                .andDo(print());
    }

    @Test
    public void registerFail() throws Exception {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("test@test.com");
        userRegisterDto.setFirstName("firstName");
        userRegisterDto.setLastName("lastName");
        userRegisterDto.setPassword("pass");
        userRegisterDto.setPasswordValidation("error");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userRegisterDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}
