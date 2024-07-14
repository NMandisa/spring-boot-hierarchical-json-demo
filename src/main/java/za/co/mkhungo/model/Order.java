package za.co.mkhungo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import za.co.mkhungo.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Entity
/*@NamedEntityGraph(
        name = "order_products_customer_entity_graph",
        attributeNodes = {@NamedAttributeNode("products"),
                @NamedAttributeNode("customer")})*/
/*@NamedEntityGraph(
        name = "order_products_customer_entity_graph",
        attributeNodes = {@NamedAttributeNode("products")})*/
@Table(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name = "order_generator", sequenceName = "sequence_order_id", allocationSize = 1, initialValue = 500)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_generator")
    @Column(name = "order_id")
    private Long id;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime placedOn;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Product> products= new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "order_belongs_to_customer",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "customer_id"))
    //@JoinColumn(name = "id", referencedColumnName = "customer_id")
    private Customer customer;

    public void addProduct(Product product){
        products.add(product);
        product.setOrder(this);
    }
    public void removeProduct(Product product){
        products.remove(product);
        product.setOrder(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Order other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
