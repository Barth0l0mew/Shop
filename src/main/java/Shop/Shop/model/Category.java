package Shop.Shop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    private static final String SEQ_NAME  = "category_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String title;
//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "categories")
// @ManyToMany (cascade = CascadeType.ALL, mappedBy = "categories")
////@JoinTable(
////        name = "products_categorys",
////        joinColumns = @JoinColumn(name = "category_id"),
////        inverseJoinColumns = @JoinColumn(name = "product_id")
////)
//    private List<Product> products;
//@ManyToMany(cascade = CascadeType.ALL)
//@JoinTable
//private List<Product> products = new ArrayList<>();

}
