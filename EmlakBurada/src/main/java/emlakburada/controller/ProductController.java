package emlakburada.controller;

import emlakburada.core.DataResult;
import emlakburada.core.Result;
import emlakburada.core.SuccessDataResult;
import emlakburada.dto.request.ProductRequest;
import emlakburada.model.Product;
import emlakburada.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Result> saveProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<Result>(productService.saveProduct(productRequest), HttpStatus.OK);

    }

    @GetMapping("/products")
    public ResponseEntity<DataResult<List<Product>>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);

    }

    @GetMapping("/products/{productName}")
    public ResponseEntity<DataResult<Product>> getProductByName(@PathVariable String productName){
        return new ResponseEntity<>(productService.getByName(productName),HttpStatus.OK);
    }

}
