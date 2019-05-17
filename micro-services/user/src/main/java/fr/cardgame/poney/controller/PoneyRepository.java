package fr.cardgame.poney.controller;

import fr.cardgame.poney.model.Poney;
import org.springframework.data.repository.CrudRepository;

import java.util.List;



public interface PoneyRepository extends CrudRepository<Poney, Integer> {
	
	public List<Poney> findByColor(String color);

}
