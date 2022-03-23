package emlakburada.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Integer price;
    private CreditCardRequest creditCardRequest;

    public PaymentRequest(Integer price){
        this.price = price;
    }

}