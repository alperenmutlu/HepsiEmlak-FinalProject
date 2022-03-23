package emlakburada.repository;

import emlakburada.model.UserProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProductDetailRepository extends JpaRepository<UserProductDetail, Integer> {
    public UserProductDetail getUserProductDetailByUserId(Integer userId);
}
