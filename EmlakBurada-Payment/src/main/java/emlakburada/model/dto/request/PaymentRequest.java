package emlakburada.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private Integer price;
    private CreditCardRequest creditCardRequest;

    public Integer getPrice() {
        return price;
    }

    public Integer setPrice(Integer price) {
        return this.price = price;
    }

}
