package za.co.mkhungo.dto;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import za.co.mkhungo.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Noxolo.Mkhungo
 */
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
public class OrderDTO {

    private Long id;
    private LocalDateTime placedOn;
    private OrderStatus orderStatus;
    private CustomerDTO customer;
    private List<ProductDTO> products= new ArrayList<>();

    public OrderDTO(){
        super();
    }

    public void addProduct(ProductDTO productDTO){
        products.add(productDTO);
        productDTO.setOrderDTO(this);
    }
    public void removeProduct(ProductDTO productDTO){
        products.remove(productDTO);
        productDTO.setOrderDTO(null);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this.getClass()).toHashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OrderDTO other)) return false;
        return new EqualsBuilder().append(this.getClass(), other.getClass()).isEquals();
    }
}
