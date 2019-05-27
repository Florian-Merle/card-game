package fr.cardgame.card.repository;


import fr.cardgame.card.model.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, Integer> {

	Card findByNameContaining(String name);

}
