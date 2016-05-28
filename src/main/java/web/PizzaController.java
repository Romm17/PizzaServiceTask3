package web;

import domain.Customer;
import domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PizzaService;
import java.util.List;

/**
 * @author Roman Usik
 */
@RestController
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello() {
        return "Hello";
    }

    @RequestMapping(value = "/pizza", method = RequestMethod.GET)
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @RequestMapping(value = "/pizza/{id}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") Integer id) {
        Pizza pizza = pizzaService.getPizzaByID(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @RequestMapping(value = "/pizza/{id}/price", method = RequestMethod.GET)
    public ResponseEntity<Double> getPizzaPriceById(@PathVariable("id") Integer id) {
        Pizza pizza = pizzaService.getPizzaByID(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza.getPrice(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/pizza/{id}/price", method = RequestMethod.PUT)
    public ResponseEntity<Integer> updatePizzaPriceById(@PathVariable("id") Integer id, @RequestBody Double price) {
        Pizza pizza = pizzaService.getPizzaByID(id);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pizza.setPrice(price);
        return new ResponseEntity<>(pizza.getId(), HttpStatus.FOUND);
    }

    @RequestMapping(value = "/pizza", method = RequestMethod.POST)
    public ResponseEntity<Integer> addPizza(@RequestBody Pizza pizza) {
        pizza.setId(42);
        return new ResponseEntity<>(pizza.getId(), HttpStatus.CREATED);
    }

}
