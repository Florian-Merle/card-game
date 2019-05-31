package fr.cardgame.user.factory;

import fr.cardgame.card.dto.Card;
import fr.cardgame.inventory.dto.AddCardDto;
import fr.cardgame.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class AddCardDtoFactory {
    public AddCardDto create(User user, Card card) {
        AddCardDto addCardDto = new AddCardDto();

        addCardDto.setAttack(card.getAttack());
        addCardDto.setDefence(card.getDefence());
        addCardDto.setDescription(card.getDescription());
        addCardDto.setEnergy(card.getEnergy());
        addCardDto.setFamily(card.getFamily());
        addCardDto.setHp(card.getHp());
        addCardDto.setIdUser(user.getId());
        addCardDto.setImgUrl(card.getImgUrl());
        addCardDto.setName(card.getName());
        addCardDto.setPrice(card.getPrice());

        return addCardDto;
    }
}
