package fr.cardgame.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.cardgame.inventory.controller.InventoryRestController;
import fr.cardgame.inventory.dto.*;
import fr.cardgame.inventory.model.Inventory;
import fr.cardgame.inventory.service.InventoryService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InventoryRestController.class, secure = false)
public class InventoryFunctionalTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @Test
    public void addCardInventory() throws Exception {
        AddCardDto addCardDto = new AddCardDto();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(addCardDto);

        when(this.inventoryService.addCardInventory(anyObject())).thenReturn(new Inventory());

        mockMvc.perform(MockMvcRequestBuilders.post("/addCardInventory")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void deleteCardInventory() throws Exception {
        DeleteCardDto deleteCardDto = new DeleteCardDto();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(deleteCardDto);

        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteCardInventory")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void getCardInventory() throws Exception {
        GetCardDto getCardDto = new GetCardDto();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(getCardDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getCardInventory")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void getOneCardInventory() throws Exception {
        GetOneCardDto getOneCardDto = new GetOneCardDto();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(getOneCardDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/getOneCardInventory")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }


    @Test
    public void updateCardInventory() throws Exception {
        UpdateCardDto updateCardDto = new UpdateCardDto();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(updateCardDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/updateCardInventory")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}
