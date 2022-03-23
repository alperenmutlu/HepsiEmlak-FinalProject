package emlakburada.dto.request;

import emlakburada.enums.AdvertStatus;
import emlakburada.enums.RealEstateType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AdvertRequest {
    private String title;
    private BigDecimal price;
    private String description;
    private RealEstateType realEstateType;
    private Integer userId;
    private AdvertStatus advertStatus;
}
