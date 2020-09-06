package pl.miknow.tacos.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.miknow.tacos.Model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
	List<Order> findByDeliveryZip(String deliveryZip);
	List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
}