package Shop.Shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ManyToAny;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "baskets")
public class Basket {
    private static final String SEQ_NAME  = "basket_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    @OneToOne
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable (name = "baskets_products",
    joinColumns = @JoinColumn (name = "basket_id"),
    inverseJoinColumns = @JoinColumn (name = "product_id"))
    private List<Product> products;

}
