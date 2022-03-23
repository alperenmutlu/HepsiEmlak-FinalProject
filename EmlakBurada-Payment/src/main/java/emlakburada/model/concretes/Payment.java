package emlakburada.model.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="paymentDate")
    private LocalDate paymentDate = LocalDate.now();

    @Column(name = "price")
    private Integer price;

    @Column(name="isSuccess")
    private String isSuccess = "true";

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="creditCard")
//    private CreditCard creditCard;

}
