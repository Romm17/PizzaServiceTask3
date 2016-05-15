package service;

import domain.Pizza;
import domain.PizzaType;

import java.util.List;

/**
 * @author Roman Usik
 */
public interface PizzaService {

    List<Pizza> getAllPizzas();

    List<Pizza> getPizzaByType(PizzaType type);

    Pizza getPizzaByID(Integer id);

    List<Pizza> getPizzaSortedByPrice();

}
