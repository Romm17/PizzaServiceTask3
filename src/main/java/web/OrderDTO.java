package web;

import domain.Customer;
import domain.Pizza;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Roman Usik
 */
@Component
@Scope(scopeName = "prototype")
public class OrderDTO {

    private Customer customer;
    private Integer[] pizzaList;

    public OrderDTO() {

    }

    public Integer[] getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(Integer[] pizzaList) {
        this.pizzaList = pizzaList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
