package web;

import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import service.PizzaService;

import java.util.List;

/**
 * @author Roman Usik
 */
@Controller
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

//    @RequestMapping(value = "pizzas", method = RequestMethod.GET)
//    public List<Pizza> getAllPizzas() {
//        return pizzaService.getAllPizzas();
//    }
}
