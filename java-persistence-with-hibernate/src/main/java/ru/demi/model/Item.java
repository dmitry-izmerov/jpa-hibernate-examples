package ru.demi.model;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import ru.demi.converter.MonetaryAmountConverter;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(
        min = 2,
        max = 255
    )
    @Access(AccessType.PROPERTY)
    @Column(name = "ITEM_NAME")
    private String name;

    @Future
    private Date auctionEnd;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdOn;

    @Column(insertable = false)
    @ColumnDefault("1.00")
    @Generated(GenerationTime.INSERT)
    private BigDecimal initialPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuctionType auctionType = AuctionType.HIGHEST_BID;

    @NotNull
    @Convert(converter = MonetaryAmountConverter.class) // optional because of autoApply = true
    @Column(name = "PRICE", length = 63)
    private MonetaryAmount buyNowPrice;

    public void setName(String name) {
        this.name = !name.startsWith("auction:") ? "auction: " + name : name;
    }
}
