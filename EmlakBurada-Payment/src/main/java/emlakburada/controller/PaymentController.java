package emlakburada.controller;

import emlakburada.model.dto.request.PaymentRequest;
import emlakburada.service.PaymentService;
import emlakburada.utils.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    public ResponseEntity<SuccessResult> makePayment(@RequestBody PaymentRequest paymentRequest){

        return new ResponseEntity<SuccessResult>(paymentService.makePayment(paymentRequest), HttpStatus.OK);

    }

}
