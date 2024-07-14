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
/*@NamedEntityGraph(
        name = "customer_orders_entity_graph",
        attributeNodes = {@NamedAttributeNode("orders")})*/
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_generator", sequenceName = "sequence_customer_id", allocationSize = 1, initialValue = 200)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @Column(name = "customer_id")
    private Long id;
    @Column(name = "customer_first_name")
    private String firstName;
    @Column(name = "customer_surname")
    private String surname;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Order> orders= new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
        order.setCustomer(this);
    }
    public void removeOrder(Order order){
        orders.remove(order);
        order.setCustomer(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Customer other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
