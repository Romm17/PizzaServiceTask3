package repository;

import domain.Pizza;
import domain.PizzaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Roman Usik
 */
public class LocalPizzaRepository implements PizzaRepository {

    private Integer autoIncrement;
    private List<Pizza> pizzaList;

    public LocalPizzaRepository() {

        autoIncrement = 1;
        pizzaList = new ArrayList<>();
//        init();

    }

    public void init(){

        pizzaList.add(new Pizza(1, PizzaType.Neapolitano, "Marinara", 10.0));
        pizzaList.add(new Pizza(2, PizzaType.Neapolitano, "Margherita", 20.0));
        pizzaList.add(new Pizza(3, PizzaType.DeepDish, "SimpleDeepDish", 30.0));
        pizzaList.add(new Pizza(4, PizzaType.Sicilian, "WithOil", 40.0));

    }

    @Override
    public Pizza getPizzaByID(Integer id) {

        for (Pizza p : pizzaList) {
            if (Objects.equals(p.getId(), id)) {
                return p;
            }
        }
        return null;

    }

    @Override
    public List<Pizza> getPizzas() {
        return pizzaList;
    }

    @Override
    public Integer savePizza(Pizza pizza) {
        pizza.setId(autoIncrement++);
        pizzaList.add(pizza);
        return autoIncrement - 1;
    }

    @Override
    public void updatePizza(Pizza pizza) {
        Pizza toUpdate = getPizzaByID(pizza.getId());
        if (toUpdate != null) {
            toUpdate.setName(pizza.getName());
            toUpdate.setType(pizza.getType());
            toUpdate.setPrice(pizza.getPrice());
        }
    }

    @Override
    public void deletePizza(Pizza pizza) {
        pizzaList.remove(pizza);
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public Integer getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Integer autoIncrement) {
        this.autoIncrement = autoIncrement;
    }
}
