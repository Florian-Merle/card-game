package fr.cardgame.user.validator;

import fr.cardgame.user.dto.UserRegisterDto;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterDtoValidator {

    /**
     * Check if a UserRegisterInDTO is valid
     *
     * @param userRegisterDto
     * @return
     */
    public boolean validate(UserRegisterDto userRegisterDto) {
        // passwords don't match
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getPasswordValidation())) {
            return false;
        }

        return true;
    }
}
