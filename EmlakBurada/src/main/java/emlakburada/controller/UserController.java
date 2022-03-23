package emlakburada.controller;

import emlakburada.core.DataResult;
import emlakburada.core.Result;
import emlakburada.core.SuccessDataResult;
import emlakburada.core.SuccessResult;
import emlakburada.dto.request.ProductRequest;
import emlakburada.dto.request.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.model.Product;
import emlakburada.model.User;
import emlakburada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<DataResult<List<UserResponse>>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<Result> saveUser(@RequestBody UserRequest userRequest){

        return new ResponseEntity<Result>(userService.saveUser(userRequest), HttpStatus.OK);
        //(Result)
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<DataResult<UserResponse>> getUserById(@PathVariable(required = false) Integer id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


    @GetMapping("/users/products/{userId}")
    public ResponseEntity<DataResult<Product>> getUserProductsByUserId(@PathVariable Integer userId){
        return new ResponseEntity<>(userService.getUserProductsByUserId(userId), HttpStatus.OK);
    }


}
