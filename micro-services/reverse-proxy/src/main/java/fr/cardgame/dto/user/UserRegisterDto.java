package fr.cardgame.dto.user;

import fr.cardgame.dto.GenericDto;

/**
 * User DTO used to handle a submitted sign-in form
 */
public class UserRegisterDto extends GenericDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String passwordValidation;

    public UserRegisterDto() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordValidation() {
        return this.passwordValidation;
    }

    public void setPasswordValidation(String passwordValidation) {
        this.passwordValidation = passwordValidation;
    }
}