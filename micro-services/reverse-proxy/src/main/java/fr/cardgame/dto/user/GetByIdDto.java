package fr.cardgame.dto.user;

import fr.cardgame.dto.AuthenticatedGenericDto;

public class GetByIdDto extends AuthenticatedGenericDto {
    private int id;

    public GetByIdDto() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
