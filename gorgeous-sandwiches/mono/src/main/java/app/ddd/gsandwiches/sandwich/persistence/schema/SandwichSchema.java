package app.ddd.gsandwiches.sandwich.persistence.schema;

import app.ddd.gsandwiches.core.persistence.BaseSchema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class SandwichSchema extends BaseSchema {

    @Column(name = "sandwichId", nullable = false, unique = true)
    private Long sandwichId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "description", nullable = false)
    private String description;

    protected SandwichSchema() {
    }

    public SandwichSchema(Long sandwichId, String name, Double price, String description) {
        this.sandwichId = sandwichId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long sandwichId() {
        return sandwichId;
    }

    public String name() {
        return name;
    }

    public Double price() {
        return price;
    }

    public String description() {
        return description;
    }

}
