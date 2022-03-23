package emlakburada.repository;

import emlakburada.model.Advert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertRepository extends JpaRepository<Advert,Integer> {
    public Advert getAdvertById(Integer id);
    public Advert getAdvertByAdvertNo(Integer id);
}
