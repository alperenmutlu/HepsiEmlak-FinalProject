package emlakburada.repository;

import emlakburada.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
     Product getById(Integer id);
     Product getByName(String name);
}
