package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Roman Usik
 */
@Component
@Scope(scopeName = "prototype")
public class Order {

    private static int count = 1;


    private Integer id;
    private LocalDateTime creationTime;
    private String name;
    private List<Pizza> pizzaList;
    private Customer customer;

    public Order() {
        id = count++;
        creationTime = LocalDateTime.now();
        name = "" + id + " : " + creationTime;
    }

    public Order(Customer customer, List<Pizza> pizzaList) {
        this();
        this.customer = customer;
        this.pizzaList = pizzaList;
    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Order.count = count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalPrice() {
        return pizzaList.stream().mapToDouble(Pizza::getPrice).sum();
    }

    @Override
    public String toString() {
        String res = "order { name : " + name + ", customer : "
                    + customer.toString()
                    + ", pizzas : {";
        Iterator<Pizza> i = pizzaList.iterator();
        for (;i.hasNext();) {
            res += i.next().toString();
            if (i.hasNext())
                res += ", ";
        }
        res += "}";
        return res;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }
}
