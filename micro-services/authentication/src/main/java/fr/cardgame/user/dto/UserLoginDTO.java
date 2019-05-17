package fr.cardgame.user.dto;

/**
 * User DTO used to handle a submitted login form
 */
public class UserLoginDTO {
    private String email;
    private String password;

    public UserLoginDTO() {
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
}
