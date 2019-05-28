package fr.cardgame.user;

import fr.cardgame.user.dto.UserRegisterDto;
import fr.cardgame.user.validator.UserRegisterDtoValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRegisterDtoValidatorUnitTests {

    @Autowired
    private UserRegisterDtoValidator userRegisterDtoValidator;

    @Test
    public void testValidateOk() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setPassword("a");
        userRegisterDto.setPasswordValidation("a");

        assertEquals(true, this.userRegisterDtoValidator.validate(userRegisterDto));
    }

    @Test
    public void testValidateFalse() {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setPassword("a");
        userRegisterDto.setPasswordValidation("b");

        assertEquals(false, this.userRegisterDtoValidator.validate(userRegisterDto));
    }
}
