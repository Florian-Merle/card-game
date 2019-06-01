package fr.cardgame.user;

import fr.cardgame.card.CardApiClient;
import fr.cardgame.card.dto.Card;
import fr.cardgame.inventory.InventoryApiClient;
import fr.cardgame.inventory.dto.AddCardDto;
import fr.cardgame.inventory.dto.Inventory;
import fr.cardgame.user.dto.UserRegisterDto;
import fr.cardgame.user.factory.AddCardDtoFactory;
import fr.cardgame.user.factory.UserFactory;
import fr.cardgame.user.model.User;
import fr.cardgame.user.repository.UserRepository;
import fr.cardgame.user.service.UserService;
import fr.cardgame.user.validator.UserRegisterDtoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTests {

    @MockBean
    private UserRepository userRepositoryMock;

    @MockBean
    private UserRegisterDtoValidator userRegisterDtoValidatorMock;

    @MockBean
    private UserFactory userFactoryMock;

    @MockBean
    private CardApiClient cardApiClient;

    @MockBean
    private InventoryApiClient inventoryApiClient;

    @MockBean
    private AddCardDtoFactory addCardDtoFactory;

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserByEmail() {
        User testUser = new User();
        when(userRepositoryMock.findByEmail("test@email.com")).thenReturn(testUser);
        assertEquals(testUser, this.userService.getUserByEmail("test@email.com"));
    }

    @Test
    public void testGetUserById() {
        User testUser = new User();
        when(userRepositoryMock.findOne(1)).thenReturn(testUser);
        assertEquals(testUser, this.userService.getUserById(1));
    }

    @Test
    public void testRegisterIncorrectDto() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();

        when(userRegisterDtoValidatorMock.validate(userRegisterDto)).thenReturn(false);

        assertEquals(null, this.userService.register(userRegisterDto));
    }

    @Test
    public void testRegisterExistingUserDto() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("test@email.com");
        User testUser = new User();

        when(userRegisterDtoValidatorMock.validate(userRegisterDto)).thenReturn(true);
        when(userRepositoryMock.findByEmail("test@email.com")).thenReturn(testUser);

        assertEquals(null, this.userService.register(userRegisterDto));
    }

    @Test
    public void testRegisterUserDtoOk() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setEmail("test@email.com");
        User testUser = new User();

        when(userRegisterDtoValidatorMock.validate(userRegisterDto)).thenReturn(true);
        when(userRepositoryMock.findByEmail("test@email.com")).thenReturn(null);
        when(userFactoryMock.createUser(userRegisterDto)).thenReturn(testUser);

        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card());
        cardList.add(new Card());
        cardList.add(new Card());
        cardList.add(new Card());
        cardList.add(new Card());

        when(cardApiClient.getCardList()).thenReturn(cardList);

        when(addCardDtoFactory.create(anyObject(), anyObject())).thenReturn(new AddCardDto());

        when(inventoryApiClient.create(anyObject())).thenReturn(new Inventory());

        assertEquals(testUser, this.userService.register(userRegisterDto));
    }
}
