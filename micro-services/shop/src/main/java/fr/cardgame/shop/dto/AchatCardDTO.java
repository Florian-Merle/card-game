package fr.cardgame.shop.dto;

import fr.cardgame.user.dto.User;

public class AchatCardDTO {

    private User user;
    private String idCard;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
