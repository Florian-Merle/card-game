package fr.cardgame.dto;

import fr.cardgame.dto.user.User;

abstract public class AuthenticatedGenericDto extends GenericDto {

    private String token;

    private User user;

    public AuthenticatedGenericDto() {
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
