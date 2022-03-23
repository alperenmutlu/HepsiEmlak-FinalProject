package emlakburada.controller;

import emlakburada.Constant.Messages;
import emlakburada.core.*;
import emlakburada.dto.request.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import emlakburada.model.Advert;
import emlakburada.service.AdvertService;
import emlakburada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@RestController
public class AdvertController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserService userService;


    @PostMapping("/adverts")
    public ResponseEntity<Result> saveAdvert(@RequestBody AdvertRequest advertRequest){
        return new ResponseEntity<Result>(advertService.saveAdvert(advertRequest), HttpStatus.CREATED);

    }

    @GetMapping("/adverts")
    public ResponseEntity<DataResult<List<AdvertResponse>>> getAllAdverts(){
        return new ResponseEntity<>(advertService.getAllAdverts(), HttpStatus.OK);
    }

    @GetMapping("/adverts/{advertNo}")
    public ResponseEntity<DataResult<AdvertResponse>> getAdvertByAdvertNo(@PathVariable(required = false) Integer advertNo){
        return new ResponseEntity<>(advertService.getAdvertByAdvertNo(advertNo), HttpStatus.OK);
    }


    //Kullanıcı sisteme giriş yaptığında kendi ilanlarını listeyebilir.
    @GetMapping("/adverts/active/user/{userId}")
    public ResponseEntity<DataResult<List<AdvertResponse>>> getActiveAdvertsByUserId(@PathVariable Integer userId){

        return new ResponseEntity<DataResult<List<AdvertResponse>>>(advertService.getActiveAdvertsByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/adverts/passive/user/{userId}")
    public ResponseEntity<DataResult<List<AdvertResponse>>> getPassiveAdvertsByUserId(@PathVariable Integer userId){

        return new ResponseEntity<DataResult<List<AdvertResponse>>>(advertService.getPassiveAdvertsByUserId(userId), HttpStatus.OK);
    }

    //Herhangi bir kullanıcı sisteme giriş yapmadan başka bir kullanıcının ilanlarını görüntüleyebilir.
    @GetMapping("/adverts/user/{userId}")
    public ResponseEntity<DataResult<List<AdvertResponse>>> getAdvertsByUserId(@PathVariable Integer userId){

        return new ResponseEntity<DataResult<List<AdvertResponse>>>(advertService.getAdvertsByUserId(userId), HttpStatus.OK);
    }

    @PostMapping("/adverts/update/{advertId}")
    public ResponseEntity<Result> updateAdvertById(@PathVariable Integer advertId, @RequestBody AdvertRequest advertRequest){

        return new ResponseEntity<>(advertService.updateAdvertById(advertId,advertRequest), HttpStatus.OK);

    }

    @PostMapping("adverts/delete/{advertId}")
    public ResponseEntity<Result> deleteAdvertById(@PathVariable Integer advertId){
        return new ResponseEntity<>(advertService.deleteAdvertById(advertId), HttpStatus.OK);
    }



}
