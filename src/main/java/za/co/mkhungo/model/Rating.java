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
        name = "rating_reviews_product_entity_graph",
        attributeNodes = {@NamedAttributeNode("reviews"), @NamedAttributeNode("product")})
@Table(name = "rating")
public class Rating {
    @Id
    @SequenceGenerator(name = "rating_generator", sequenceName = "sequence_rating_id", allocationSize = 1, initialValue = 300)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_generator")
    @Column(name = "rating_id")
    private Long id;
    @Column(name = "rating")
    private int rate;

    @OneToMany(mappedBy = "rating",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @Fetch(FetchMode.SELECT)
    private List<Review> reviews= new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "rating_belong_to_product",
            joinColumns = @JoinColumn(name = "rating_id", referencedColumnName = "rating_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
    //@JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    public void addReview(Review review){
        reviews.add(review);
        review.setRating(this);
    }
    public void removeReview(Review review){
        reviews.remove(review);
        review.setRating(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rating other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
