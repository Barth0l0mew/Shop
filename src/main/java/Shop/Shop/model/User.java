package Shop.Shop.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User {
    private static final String SEQ_NAME  = "user_seq";
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column (name = "id")
    private Long id;
    @Column (name = "username")
    private String username;
    @Column (name = "password")
    private String password;
    @Column (name = "email")
    private String email;
    @Column (name = "archive")
    private boolean archive;
    @Enumerated(EnumType.STRING)
    private Role role;
// рабочий вариант 1
    //, orphanRemoval = true,fetch = FetchType.EAGER
/*@JsonIgnore
@OneToOne( cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
@JoinColumn (name = "basket_id")
private Basket basket;
*/
@ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
@JoinTable(
        name = "users_products",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
)
private List<Product> products = new ArrayList<>();

//    @OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @JoinColumn (name ="basket_id")
//    private Basket basket;


    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@OneToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
   // @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "basket_id", referencedColumnName = "id")

//    @ManyToMany
//    private List<Product> products;
   // @OneToOne (cascade = CascadeType.REMOVE)
//   @JsonManagedReference
  // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    @OneToOne(cascade = CascadeType.ALL)
//   @JoinColumn(name = "basket_id", referencedColumnName = "id")
//    private Basket basket;
//@ManyToMany (cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
//cascade = {CascadeType.MERGE, CascadeType.PERSIST}
//@JoinTable(
//        name = "users_products",
//        joinColumns = @JoinColumn(name = "user_id"),
//        inverseJoinColumns = @JoinColumn(name = "product_id")
//)
//    @ManyToMany
//private List<Product> products ;



}
