package ru.demi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Organization entity.
 */
@Data
@Accessors(chain = true)
@Entity(name = "organization")
@DiscriminatorColumn(name = "organization_type")
@DiscriminatorOptions(force = true)
@DiscriminatorValue("organization")
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"inn", "kpp"})
public class Organization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private OrganizationType type;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "registration_date")
    private Date registrationDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "inn")
    private String inn;

    @Column(name = "kib")
    private Boolean kib;

    @Column(name = "okpo")
    private String okpo;

    @Column(name = "okogu")
    private String okogu;

    @Column(name = "okato")
    private String okato;

    @Column(name = "oktmo")
    private String oktmo;

    @Column(name = "okopf")
    private String okopf;

    @Column(name = "okfs")
    private String okfs;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "opf")
    private String opf;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "registration_address_id")
    private RegistrationAddress registrationAddress;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "postal_address_id")
    private PostalAddress postalAddress;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "actual_address_id")
    private ActualAddress actualAddress;

    @LazyCollection(LazyCollectionOption.TRUE)
    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL)
    private List<SubDivision> subDivisions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "main_id")
    private Organization mainOrganization;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrganizationStatus status;

    @Column(name = "client_id")
    private Long clientId;

    public boolean isClosed() {
        return this.status == OrganizationStatus.CLOSED;
    }

    public boolean isUnknown() {
        return this.status == OrganizationStatus.UNKNOWN;
    }

    public boolean isActive() {
        return this.status == OrganizationStatus.ACTIVE_CLIENT;
    }
}
