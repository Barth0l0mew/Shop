package Shop.Shop.model;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    private static final String SEQ_NAME  = "product_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String title;
    private BigDecimal price;
    private String description;
    //@ManyToMany (cascade = CascadeType.ALL)
//    @ManyToMany
//    @JoinTable(
//            name = "product_category",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id")
//    )
    // @ManyToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "products_categorys",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;
//    @ManyToMany(mappedBy = "products",cascade = CascadeType.ALL)
//    private List<Category> categories = new ArrayList<>();
//    public void removeCategory(Category category) {
//        this.categories.remove(category);
//        category.getProducts().remove(this);
//    }
//    @ManyToMany
//    @JoinTable (name = "products_categories",
//            joinColumns = @JoinColumn (name = "product_id"),
//            inverseJoinColumns = @JoinColumn (name = "category_id"))
//    private List<Category> categories;
//    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
//    private List<Category> categories;
}
