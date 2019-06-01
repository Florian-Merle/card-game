package fr.cardgame.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.cardgame.shop.controller.ShopRestController;
import fr.cardgame.shop.dto.AchatCardDTO;
import fr.cardgame.shop.dto.VenteCardDTO;
import fr.cardgame.shop.service.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ShopRestController.class, secure = false)
public class ShopFunctionalTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService shopService;

    @Test
    public void buyTestFail() throws Exception {
        AchatCardDTO achatCardDTO = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(achatCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/buy")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void buyTestOk() throws Exception {
        AchatCardDTO achatCardDTO = new AchatCardDTO();
        achatCardDTO.setIdCard("1");

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(achatCardDTO);

        when(shopService.buy(anyObject(), eq("1"))).thenReturn("Ok");

        mockMvc.perform(MockMvcRequestBuilders.post("/buy")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(content().string("Ok"))
                .andDo(print());
    }

    @Test
    public void sellTestFail() throws Exception {
        VenteCardDTO venteCardDTO = null;

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(venteCardDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/sell")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }



    @Test
    public void buySellOk() throws Exception {
        VenteCardDTO venteCardDTO = new VenteCardDTO();
        venteCardDTO.setIdInventory(1);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(venteCardDTO);

        when(shopService.sell(anyObject(), eq(1))).thenReturn("Ok");

        mockMvc.perform(MockMvcRequestBuilders.post("/sell")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(content().string("Ok"))
                .andDo(print());
    }
}
