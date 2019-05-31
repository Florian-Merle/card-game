package fr.cardgame.dto.inventory;

import fr.cardgame.dto.AuthenticatedGenericDto;
import fr.cardgame.dto.user.User;

public class GetCardDto extends AuthenticatedGenericDto {

    private User user;

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
