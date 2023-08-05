package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.util.List;

public interface RentalOrderDAO {
    void saveRentalOrder(RentalOrder rentalOrder);

    List<RentalOrder> findOpenRentalOrdersByEmail(String email);

    void changeOrderStatusByOrderId(Integer rentOrderId, OrderStatus orderStatus);

    void changeCarToRentStatus(Integer carToRentId, CarStatus carStatus);

    List<OrderAndCar> findOrdersByStatusJoinedWithCars(OrderStatus orderStatus);

}
