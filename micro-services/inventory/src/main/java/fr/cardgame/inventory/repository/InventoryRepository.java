package fr.cardgame.inventory.repository;

import fr.cardgame.inventory.model.Inventory;
import org.springframework.data.repository.CrudRepository;

/**
 * interface InventoryRepository
 *
 * this interface is used by spring boot and will give you an object implementing all the functions defined here
 *
 * to use it:
 * @Autowired
 * private InventoryRepository inventoryRepository;
 */
public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

}
