package fr.cardgame.dto.inventory;

import fr.cardgame.dto.AuthenticatedGenericDto;

public class DeleteCardDto extends AuthenticatedGenericDto {

    private Integer id;

    public DeleteCardDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
