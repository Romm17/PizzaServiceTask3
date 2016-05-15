package repository;

import domain.Pizza;

import java.util.List;

/**
 * @author Roman Usik
 */
public interface PizzaRepository {

    Pizza getPizzaByID(Integer id);

    List<Pizza> getPizzas();

    Integer savePizza(Pizza pizza);

    void updatePizza(Pizza pizza);

    void deletePizza(Pizza pizza);

}
