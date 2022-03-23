package emlakburada.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import emlakburada.Constant.Messages;
import emlakburada.core.DataResult;
import emlakburada.core.Result;
import emlakburada.core.SuccessDataResult;
import emlakburada.dto.request.ProductRequest;
import emlakburada.model.Product;
import emlakburada.repository.ProductRepository;
import emlakburada.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void getProductByName(){

        Product product = prepareProduct(100);

        Mockito
                .when(productRepository.getByName(product.getName()))
                .thenReturn(product);

        DataResult<Product> foundProduct = productService.getByName("advertpackage");

        assertNotNull(foundProduct);
        assertEquals("advertpackage",foundProduct.getData().getName());


    }


    @Test
    void getAllProducts(){

        Mockito
                .when(productRepository.findAll())
                .thenReturn(prepareProductList());

        DataResult<List<Product>> productList = productService.getAllProducts();

        assertNotNull(productList);
        assertNotEquals(0,productList.getData().size());

    }

    private List<Product> prepareProductList() {
        List<Product> productList = new ArrayList<>();
        productList.add(prepareProduct(100));
        productList.add(prepareProduct(100));
        return productList;
    }


    @Test
    void saveProduct(){

        Mockito
                .when(productRepository.save(prepareProduct(100)))
                .thenReturn(any());

        Result result = productService.saveProduct(prepareProductRequest());

        assertEquals(result.getMessage(), Messages.productAdded);
        assertEquals("Product added!",result.getMessage());

    }

    private Product prepareProduct(Integer price){
        Product product = new Product();
        product.setId(1);
        product.setName("advertpackage");
        product.setPrice(price);
        return product;
    }

    private ProductRequest prepareProductRequest(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setPrice(100);
        return productRequest;
    }

}
