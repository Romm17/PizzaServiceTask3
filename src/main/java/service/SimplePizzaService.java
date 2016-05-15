package service;

import domain.Pizza;
import domain.PizzaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PizzaRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Roman Usik
 */
@Service("pizzaService")
public class SimplePizzaService implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Override
    public List<Pizza> getAllPizzas() {
        return pizzaRepository.getPizzas();
    }

    @Override
    public List<Pizza> getPizzaByType(PizzaType type) {
        return getAllPizzas().stream()
                .filter(pizza -> pizza.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        return pizzaRepository.getPizzaByID(id);
    }

    @Override
    public List<Pizza> getPizzaSortedByPrice() {
        List<Pizza> pizzas = getAllPizzas();
        pizzas.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        return pizzas;
    }
}
