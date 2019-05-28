package fr.cardgame.user;

import fr.cardgame.user.dto.UserRegisterDto;
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

import static org.junit.Assert.assertEquals;
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

        assertEquals(testUser, this.userService.register(userRegisterDto));
    }
}
