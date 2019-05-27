package fr.cardgame.dto.inventory;

import fr.cardgame.dto.AuthenticatedGenericDto;

public class GetCardDto extends AuthenticatedGenericDto {

    private Integer idUser;

    public GetCardDto() {
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}
