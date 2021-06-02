package stupnytskyi.rostyslav.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stupnytskyi.rostyslav.shop.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
