package fr.cardgame.dto.user;

import fr.cardgame.dto.AuthenticatedGenericDto;

public class GetByEmailDto extends AuthenticatedGenericDto {
    private String email;

    public GetByEmailDto() {
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
