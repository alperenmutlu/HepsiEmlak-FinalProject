package emlakburada.dto.request;

import emlakburada.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    //private Integer id;
    String name;
    String description;
    Integer price;

}
