package emlakburada.service;

import emlakburada.Constant.Messages;
import emlakburada.core.*;
import emlakburada.dto.request.ProductRequest;
import emlakburada.model.Product;
import emlakburada.repository.ProductRepository;
import emlakburada.service.BaseService.ProductBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends ProductBaseService {

    @Autowired
    private ProductRepository productRepository;

    public Result saveProduct(ProductRequest productRequest){
        productRepository.save(convertToProductEntity(productRequest));
        return new SuccessResult(Messages.productAdded);
    }

    public DataResult<List<Product>> getAllProducts(){

        return new SuccessDataResult<>(productRepository.findAll(), Messages.packagesListed);

    }

    public DataResult<Product> getByName(String productName){
        Product product = productRepository.getByName(productName);
        if(product != null){
            return new SuccessDataResult<>(product,Messages.packageListedByName);
        }
        return new ErrorDataResult<>(Messages.packageIsNotFound);
    }

//    public DataResult<Product> getById(Integer productId){
//        Product product = productRepository.getById(productId);
//        if(product != null){
//            return new SuccessDataResult<>(product,Messages.packageListedById);
//        }
//        return new ErrorDataResult<>(Messages.packageIsNotFound);
//    }

}
