package ru.demi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@Entity
@Table(name = "category_item")
@Immutable
public class CategorizedItem {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "category_id")
        private Long categoryId;
        @Column(name = "item_id")
        private Long itemId;
    }

    @EmbeddedId
    private Id id = new Id();

    @Column(updatable = false)
    @NotNull
    private String addedBy;

    @Column(updatable = false)
    @NotNull
    private Date addedOn = new Date();

    @ManyToOne
    @JoinColumn(
        insertable = false,
        updatable = false
    )
    private Category category;

    @ManyToOne
    @JoinColumn(
        insertable = false,
        updatable = false
    )
    private Item item;

    public CategorizedItem(String addedBy, Category category, Item item) {
        this.addedBy = addedBy;
        this.category = category;
        this.item = item;

        this.id.categoryId = category.getId();
        this.id.itemId = item.getId();

        category.getCategorizedItems().add(this);
        item.getCategorizedItems().add(this);
    }
}
