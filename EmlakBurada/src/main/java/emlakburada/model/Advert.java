package emlakburada.model;

import emlakburada.enums.AdvertStatus;
import emlakburada.enums.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column (name = "advertNo")
    private Integer advertNo;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="realEstateType")
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="creator_user_id")
    private User creatorUser;

    @Column(name="createdDate")
    private LocalDate createdDate = LocalDate.now();

    @Column(name="advertStatus")
    @Enumerated(EnumType.STRING)
    private AdvertStatus advertStatus = AdvertStatus.IN_REVIEW;


}
