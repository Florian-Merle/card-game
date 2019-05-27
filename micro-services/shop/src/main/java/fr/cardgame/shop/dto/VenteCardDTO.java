package fr.cardgame.shop.dto;

import fr.cardgame.user.dto.User;

public class VenteCardDTO {

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
