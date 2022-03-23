package emlakburada.service;

import emlakburada.Constant.Messages;
import emlakburada.client.PaymentClient;
import emlakburada.client.request.PaymentRequest;
import emlakburada.core.DataResult;
import emlakburada.core.ErrorResult;
import emlakburada.core.Result;
import emlakburada.core.SuccessResult;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Product;
import emlakburada.model.User;
import emlakburada.model.UserProductDetail;
import emlakburada.repository.ProductRepository;
import emlakburada.repository.UserProductDetailRepository;
import emlakburada.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.Period;


@Service
@Slf4j
public class UserProductDetailService {

    @Autowired
    private UserProductDetailRepository userProductDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentClient paymentClient;



    public Result saveProductToUser(Integer productId, Integer userId){

        Product product = productRepository.getById(productId);

        User user = userRepository.getUserById(userId);

        UserProductDetail userProductDetail;


        if(user.getProduct() != null){

            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setPrice(product.getPrice());

            paymentClient.makePayment(paymentRequest);
            log.info("ÖDEME YAPILIYOR");

           userProductDetail = userProductDetailRepository.getUserProductDetailByUserId(user.getId());


            Period diff = Period.between(LocalDate.now(),userProductDetail.getEndDate());

            Integer diffDays = diff.getDays();

            userProductDetail.setPurchaseDate(LocalDate.now());
            userProductDetail.setEndDate(userProductDetail.getEndDate().plusDays(diffDays));


            userProductDetailRepository.save(userProductDetail);

            Integer advertNumberOfUser = user.getNumberOfAdverts();

            user.setNumberOfAdverts(advertNumberOfUser + product.getNumberOfAdverts());
            userRepository.save(user);

                //kullanıcı hakları güncellendi!
            return new SuccessResult(Messages.userAdvertRightsUpdated);

        }


        if(user.getProduct() == null && user != null && product !=null){

            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setPrice(product.getPrice());

            paymentClient.makePayment(paymentRequest);
            log.info("ÖDEME YAPILIYOR");


            userProductDetail = new UserProductDetail();

            userProductDetail.setUser(user);
            userProductDetail.setProduct(product);
            userProductDetail.setPurchaseDate(LocalDate.now());
            userProductDetail.setEndDate(LocalDate.now().plusDays(30));


            userProductDetailRepository.save(userProductDetail);

            user.setProduct(product);
            user.setNumberOfAdverts(product.getNumberOfAdverts());
            userRepository.save(user);

            //kullanıcıya paket eklendi
            return new SuccessResult(Messages.packageAddedToUser);
        }

        //hata
        return new ErrorResult(Messages.packageCouldNotAddedToUser);


    }

}
