package emlakburada.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardRequest {
    private String cardHolderName;
    private Integer expirationMonth;
    private Integer expirationyear;
    private String cardNumber;
    private String cvv;
}
