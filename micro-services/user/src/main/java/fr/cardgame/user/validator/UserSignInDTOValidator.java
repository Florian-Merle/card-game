package fr.cardgame.user.validator;

import fr.cardgame.user.dto.UserRegisterInDTO;

public class UserSignInDTOValidator {

    /**
     * Check if a UserRegisterInDTO is valid
     *
     * @param userRegisterInDTO
     * @return
     */
    public boolean validate(UserRegisterInDTO userRegisterInDTO) {
        // passwords don't match
        if (!userRegisterInDTO.getPassword().equals(userRegisterInDTO.getPasswordValidation())) {
            return false;
        }

        return true;
    }
}
