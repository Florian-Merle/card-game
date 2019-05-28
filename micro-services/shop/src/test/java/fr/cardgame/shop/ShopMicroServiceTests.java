package fr.cardgame.shop;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.cardgame.shop.dto.AchatCardDTO;
import fr.cardgame.shop.dto.VenteCardDTO;
import fr.cardgame.user.dto.User;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopMicroServiceTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

/*
    //buy
    @Test
    public void buy() throws Exception {
    	String url ="/buy";
    	AchatCardDTO cardDTO = new AchatCardDTO();
    	//TODO real ID and real user
    	cardDTO.setIdCard("idCard");
    	cardDTO.setUser(new User());

    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cardDTO );

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(requestJson))
            .andExpect(status().isOk());
    }
    
    //buy mais plus d'argent
    @Test
    public void buyWithoutMoney() throws Exception {
    	String url ="/buy";
    	AchatCardDTO cardDTO = new AchatCardDTO();
    	User user = new User();
    	user.setCash(0);
    	//TODO real ID and real user
    	cardDTO.setIdCard("idCard");
    	cardDTO.setUser(user);

    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cardDTO );

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(requestJson))
        	.andExpect(content().string(""))
        	.andDo(print());
    }

    //sell
    @Test
    public void sell() throws Exception {
    	String url ="/sell";
    	VenteCardDTO cardDTO = new VenteCardDTO();
        
    	//TODO real ID and real user
    	cardDTO.setIdInventory(1);
    	cardDTO.setUser(new User());
    	
    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cardDTO );

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(requestJson))
            .andExpect(status().isOk());
    }
    
    
    //sell d'une carte non possédé
    @Test
    public void sellWithoutHave() throws Exception {
    	String url ="/sell";
    	VenteCardDTO cardDTO = new VenteCardDTO();

    	//TODO real ID and real user
    	cardDTO.setIdInventory(null);
    	cardDTO.setUser(new User());
    	
    	ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(cardDTO );

        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(requestJson))
            .andExpect(status().isNotFound());
    }*/
}
