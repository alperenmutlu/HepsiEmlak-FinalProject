package emlakburada.client;

import emlakburada.client.request.PaymentRequest;
import emlakburada.core.SuccessResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "EmlakBurada-Payment", url = "http://localhost:8081")
public interface PaymentClient {
    @PostMapping("/payments")
    ResponseEntity<SuccessResult> makePayment(@RequestBody PaymentRequest paymentRequest);
}
