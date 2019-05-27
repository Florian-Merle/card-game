package fr.cardgame.dto;

import fr.cardgame.dto.user.User;

public class AchatCardDTO extends AuthenticatedGenericDto {

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
