package fr.cardgame.dto;


import fr.cardgame.dto.user.User;

public class VenteCardDTO extends AuthenticatedGenericDto {

    private User user;
    private Integer idInventory;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIdInventory() {
        return idInventory;
    }

    public void setIdInventory(Integer idInventory) {
        this.idInventory = idInventory;
    }
}
