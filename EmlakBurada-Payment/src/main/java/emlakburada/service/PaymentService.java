package emlakburada.service;

import emlakburada.model.concretes.Payment;
import emlakburada.model.dto.request.PaymentRequest;
import emlakburada.repository.PaymentRepository;
import emlakburada.utils.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public SuccessResult makePayment(PaymentRequest paymentRequest){

        Payment payment = paymentRepository.save(convertToPaymentEntity(paymentRequest));

        return new SuccessResult(true,"Ödeme başarılı");
    }

    private Payment convertToPaymentEntity(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setPrice(paymentRequest.getPrice());
        return payment;
    }

}
