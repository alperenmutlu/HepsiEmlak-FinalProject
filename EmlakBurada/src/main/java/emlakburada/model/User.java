package emlakburada.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import emlakburada.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="userType")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="photo")
    private String photo;

    @Column(name="bio")
    private String bio;

    @Column(name="phone")
    private String phone;

    @Column(name="numberOfAdverts")
    private Integer numberOfAdverts = 0;

    @OneToMany(mappedBy = "creatorUser", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Advert> advertList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;



    public User(UserType userType, String name, String email){
        super();
        this.userType = userType;
        this.name = name;
        this.email = email;
    }


}
