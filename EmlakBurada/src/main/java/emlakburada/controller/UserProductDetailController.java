package emlakburada.controller;

import emlakburada.core.Result;
import emlakburada.service.UserProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserProductDetailController {

    @Autowired
    private UserProductDetailService userProductDetailService;

    @PostMapping("/userProductDetail/{productId}/{userId}")
    public ResponseEntity<Result>  saveProductToUser(@PathVariable Integer productId, @PathVariable Integer userId){
        return new ResponseEntity<>(userProductDetailService.saveProductToUser(productId,userId), HttpStatus.OK);
    }

}
