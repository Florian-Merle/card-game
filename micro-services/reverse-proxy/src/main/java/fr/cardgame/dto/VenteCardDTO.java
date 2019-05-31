package fr.cardgame.dto;


import fr.cardgame.dto.user.User;

public class VenteCardDTO extends AuthenticatedGenericDto {

    private Integer idInventory;

    public Integer getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Integer idInventory) {
        this.idInventory = idInventory;
    }
}
