package fr.cardgame.dto.authentication;

import fr.cardgame.dto.GenericDto;

/**
 * Token given to the user
 */
public class TokenDto implements GenericDto {
    private String token;

    public TokenDto() {
    }

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
