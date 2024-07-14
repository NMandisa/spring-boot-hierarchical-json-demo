package za.co.mkhungo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Entity
@NamedEntityGraph(
        name = "product_ratings_order_entity_graph",
        attributeNodes = {@NamedAttributeNode("ratings"),
                @NamedAttributeNode("order")})
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator(name = "product_generator", sequenceName = "sequence_product_id", allocationSize = 1, initialValue = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "product_price")
    private Double price;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Rating> ratings= new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_has_order",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"))
    //@JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    public void addRating(Rating rating){
        ratings.add(rating);
        rating.setProduct(this);
    }
    public void removeRating(Rating rating){
        ratings.remove(rating);
        rating.setProduct(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
