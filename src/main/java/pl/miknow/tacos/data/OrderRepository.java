package pl.miknow.tacos.data;

import pl.miknow.tacos.Order;

public interface OrderRepository {
	Order save(Order design);
}
