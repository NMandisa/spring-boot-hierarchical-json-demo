package za.co.mkhungo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Entity
@NamedEntityGraph(
        name = "review_rating_entity_graph",
        attributeNodes = {@NamedAttributeNode("rating")})
@Table(name = "review")
public class Review {
    @Id
    @SequenceGenerator(name = "review_generator", sequenceName = "sequence_review_id", allocationSize = 1, initialValue = 400)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_generator")
    @Column(name = "review_id")
    private Long id;
    @Column(name = "review_tag_line")
    private String tagLine;
    @Column(name = "review_comment")
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   // @JoinColumn(name = "rating_id", referencedColumnName = "rating_id")
    @JoinTable(
            name = "review_belong_to_rating",
            joinColumns = @JoinColumn(name = "review_id", referencedColumnName = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id", referencedColumnName = "rating_id"))
    private Rating rating;

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Review other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
