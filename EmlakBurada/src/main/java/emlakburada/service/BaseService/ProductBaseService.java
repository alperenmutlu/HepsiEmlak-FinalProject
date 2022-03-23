package emlakburada.service.BaseService;

import emlakburada.dto.request.ProductRequest;
import emlakburada.model.Product;

public class ProductBaseService {

    public Product convertToProductEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        return product;
    }

}
