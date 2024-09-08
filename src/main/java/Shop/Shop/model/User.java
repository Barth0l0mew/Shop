package Shop.Shop.model;


import jakarta.persistence.*;
import lombok.*;


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
    @OneToOne (cascade = CascadeType.REMOVE)
    private Basket basket;



}
