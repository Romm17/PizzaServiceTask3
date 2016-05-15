package domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Roman Usik
 */
@Component
@Scope(scopeName = "prototype")
public class Customer {

    private Integer id;
    private String name;

    public Customer() {

    }

    public Customer(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "{ id : " + id + ", name : " + name + "}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
