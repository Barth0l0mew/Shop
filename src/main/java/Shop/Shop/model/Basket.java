package Shop.Shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ManyToAny;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    //рабочий вариант
  //  @JsonIgnore
   // @OneToOne (mappedBy = "basket")
    // private User user;
    //, referencedColumnName = "id"
  //  @JoinColumn(name = "user_id")
   // private User user;
    private String title;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(
//            name = "basket_product",
//            joinColumns = @JoinColumn(name = "basket_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id") )
 //   private Set<Product> products = new HashSet<>();
//    @JsonBackReference

    /*
    @OneToOne (mappedBy = "basket", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private User user;
    */

    //@OneToOne
   // @JoinColumn(name = "user_id")
   // @JsonBackReference
    //@OneToOne(mappedBy = "basket")
//@OneToOne(cascade = CascadeType.ALL)

//    @ManyToMany
//    @JoinTable (name = "baskets_products",
//    joinColumns = @JoinColumn (name = "basket_id"),
//    inverseJoinColumns = @JoinColumn (name = "product_id"))
//    private List<Product> products;

}
