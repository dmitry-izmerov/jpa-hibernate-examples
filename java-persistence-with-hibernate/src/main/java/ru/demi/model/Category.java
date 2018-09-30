package ru.demi.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Accessors(chain = true)
@Data
@EqualsAndHashCode(of = "id")
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "category_item",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items = new HashSet<>();
}
