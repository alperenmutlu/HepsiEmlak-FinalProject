package emlakburada.model.concretes;

import javax.persistence.*;
import java.util.List;

//@Entity
public class CreditCard  /*implements PaymentModel*/ {


    private Integer id;

    //@Column(name="cardHolderName")
    private String cardHolderName;

   // @Column(name="expirationMonth")
    private Integer expirationMonth;

   // @Column(name="expirationyear")
    private Integer expirationyear;

   // @Column(name="cardNumber")
    private String cardNumber;

    //@Column(name = "cvv")
    private String cvv;

//    @OneToMany
//    private List<Payment> paymentList;
}
