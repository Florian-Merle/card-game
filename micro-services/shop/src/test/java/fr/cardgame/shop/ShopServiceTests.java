package fr.cardgame.shop;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import fr.cardgame.card.client.CardApiClient;
import fr.cardgame.card.dto.CardDto;
import fr.cardgame.inventory.DeleteCardDto;
import fr.cardgame.inventory.GetOneCardDto;
import fr.cardgame.inventory.Inventory;
import fr.cardgame.inventory.InventoryApiClient;
import fr.cardgame.inventory.InventoryDto;
import fr.cardgame.shop.dto.AchatCardDTO;
import fr.cardgame.shop.dto.VenteCardDTO;
import fr.cardgame.shop.service.ShopService;
import fr.cardgame.user.client.UserApiClient;
import fr.cardgame.user.dto.UpdateUserCashDto;
import fr.cardgame.user.dto.User;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.assertj.core.condition.AnyOf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopServiceTests {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    
    @Autowired
    private ShopService shopService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @MockBean
    CardApiClient cardApi;
    
    @MockBean
    InventoryApiClient inventoryApi;
    
    @MockBean
    UserApiClient userApi;
    //buy
    @Test
    public void buyTest() throws Exception {
    	
    	CardDto card = new CardDto();
    	
    	card.setAttack(1);
    	card.setDefence(1);
    	card.setDescription("desc");
    	card.setFamily("fam");
    	card.setHp(1);
    	card.setId(999);
    	card.setImgUrl("url");
    	card.setName("test");
    	card.setPrice(10);
    	
    	User user = new User();
    	
    	user.setId(999);
    	user.setCash(999999);

    	when(cardApi.getCardById("999")).thenReturn(card);
    	when(inventoryApi.addInventory(any(InventoryDto.class))).thenReturn(true);
    	when(userApi.updateUserCash(any(UpdateUserCashDto.class))).thenReturn(true);
    	
    	assertEquals("Transaction OK", shopService.buy(user,"999"));
    }
    
    //buy mais plus d'argent
    @Test
    public void buyWithoutMoneyTest() throws Exception {
    	
    	CardDto card = new CardDto();
    	
    	card.setAttack(1);
    	card.setDefence(1);
    	card.setDescription("desc");
    	card.setFamily("fam");
    	card.setHp(1);
    	card.setId(999);
    	card.setImgUrl("url");
    	card.setName("test");
    	card.setPrice(10);
    	
    	User user = new User();
    	
    	user.setId(999);
    	user.setCash(0);

    	when(cardApi.getCardById("999")).thenReturn(card);
    	when(inventoryApi.addInventory(any(InventoryDto.class))).thenReturn(true);
    	when(userApi.updateUserCash(any(UpdateUserCashDto.class))).thenReturn(true);
    	
    	assertEquals("Fond insuffisant", shopService.buy(user,"999"));
    }

    //sell
    @Test
    public void sellTest() throws Exception {

    	
    	User user = new User();
    	
    	user.setId(999);
    	user.setCash(0);
    	Inventory inve = new Inventory();
    	inve.setPrice(5);
    	when(inventoryApi.getCardInventory(any(GetOneCardDto.class))).thenReturn(inve);
    	when(userApi.updateUserCash(any(UpdateUserCashDto.class))).thenReturn(true);
    	when(inventoryApi.deleteInventory(any(DeleteCardDto.class))).thenReturn(true);
    	

    	assertEquals("Vente OK", shopService.sell(user,1));
    }
    
    
    //sell d'une carte non possédé
    @Test
    public void sellWithoutHave() throws Exception {
    	User user = new User();
    	user.setId(999);
    	user.setCash(0);
    	Inventory inve = null;
    	when(inventoryApi.getCardInventory(any(GetOneCardDto.class))).thenReturn(inve);
    	when(userApi.updateUserCash(any(UpdateUserCashDto.class))).thenReturn(true);
    	when(inventoryApi.deleteInventory(any(DeleteCardDto.class))).thenReturn(true);
    	

    	assertEquals("Vente echoue", shopService.sell(user,1));
    }
}
