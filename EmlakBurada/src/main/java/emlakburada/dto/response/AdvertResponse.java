package emlakburada.dto.response;

import emlakburada.enums.AdvertStatus;
import emlakburada.enums.RealEstateType;
import emlakburada.model.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class AdvertResponse {
    private Integer advertNo;
    private String title;
    private String description;
    //private User user;
    //private UserResponse userResponse;
    private Integer userId;
    private RealEstateType realEstateType;
    private BigDecimal price;
    private LocalDate createdDate;
    private AdvertStatus advertStatus;
}
