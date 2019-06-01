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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardMicroServiceFunctionalTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void getById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cards/getById/5").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andDo(print());
    }

    @Test
    public void getByIdNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cards/getById/5678").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }

    @Test
    public void getByName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cards/getByName/Carapuce").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Carapuce"))
                .andDo(print());
    }

    @Test
    public void getByNamePartial() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cards/getByName/Carap").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Carapuce"))
                .andDo(print());
    }

    @Test
    public void getByNameNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cards/getByName/pikapika").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }


}
