package fr.cardgame.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFunctionalTests {


    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Test
    public void contextLoads() {
    }

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void getUserByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/getUserByEmail").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andDo(print());
    }
}
