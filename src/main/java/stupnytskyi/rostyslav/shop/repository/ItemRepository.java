package stupnytskyi.rostyslav.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stupnytskyi.rostyslav.shop.entity.Item;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {


    Optional<Item> findFirstByTitleContainsIgnoreCaseOrderByPriceAsc(String title);

    List<Item> findAllByTitleContainsIgnoreCaseAndAvailableQuantityGreaterThanEqual(String title, Integer quantity);
}
