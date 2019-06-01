package fr.cardgame.user;

import fr.cardgame.inventory.dto.*;
import fr.cardgame.inventory.factory.InventoryFactory;
import fr.cardgame.inventory.model.Inventory;
import fr.cardgame.inventory.repository.InventoryRepository;
import fr.cardgame.inventory.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceUnitTests {

    @Autowired
    private InventoryService inventoryService;

    @MockBean
    private InventoryRepository inventoryRepository;

    @MockBean
    private InventoryFactory inventoryFactory;

    @Test
    public void addCardInventory() {
        AddCardDto addCardDto = new AddCardDto();

        Inventory inventory = new Inventory();

        when(inventoryFactory.createInventory(addCardDto)).thenReturn(inventory);

        assertEquals(inventory , this.inventoryService.addCardInventory(addCardDto));
    }

    @Test
    public void getCardInventory() {
        GetCardDto addCardDto = new GetCardDto();
        User user = new User();
        user.setId(1);
        addCardDto.setUser(user);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(new Inventory());
        inventoryList.add(new Inventory());
        inventoryList.add(new Inventory());
        inventoryList.add(new Inventory());

        when(inventoryRepository.findByIdUser(1)).thenReturn(inventoryList);

        assertEquals(inventoryList , this.inventoryService.getCardInventory(addCardDto));
    }

    @Test
    public void getOneCardInventory() {
        GetOneCardDto getOneCardDto = new GetOneCardDto();
        getOneCardDto.setId(1);

        Inventory inventory = new Inventory();

        when(inventoryRepository.findById(1)).thenReturn(inventory);

        assertEquals(inventory , this.inventoryService.getOneCardInventory(getOneCardDto));
    }


    @Test
    public void updateCardInventory() {
        UpdateCardDto updateCardDto = new UpdateCardDto();
        updateCardDto.setIdCard(1);
        updateCardDto.setEnergy(-99);

        Inventory inventory = new Inventory();

        when(inventoryRepository.findById(1)).thenReturn(inventory);

        assertEquals(inventory , this.inventoryService.updateCardInventory(updateCardDto));
        assertEquals(-99 , inventory.getEnergy());
    }
}
