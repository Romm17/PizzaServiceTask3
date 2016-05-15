package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Roman Usik
 */
@Component
@Scope(scopeName = "prototype")
public class Pizza {

    private Integer id;
    private PizzaType type;
    private String name;
    private Double price;

    public Pizza() {

    }

    public Pizza(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Pizza(Integer id, PizzaType type, String name, Double price) {
        this(id, name);
        this.type = type;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{\"id\" : \"" + id + "\", \"name\" : \"" + name + "\", \"price\" : \"" + price + "\"}";
    }
}
