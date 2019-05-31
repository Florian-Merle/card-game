package fr.cardgame.dto.inventory;

import fr.cardgame.dto.AuthenticatedGenericDto;

public class GetOneCardDto extends AuthenticatedGenericDto {

    private Integer id;

    public GetOneCardDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
