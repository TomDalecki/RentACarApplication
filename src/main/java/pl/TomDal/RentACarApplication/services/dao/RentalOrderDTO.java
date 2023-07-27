package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.util.List;

public interface RentalOrderDTO {
    void saveRentalOrder(RentalOrder rentalOrder);

    List<RentalOrder> findOpenRentalOrdersByEmail(String email);

    void changeOrderStatus(Integer rentOrderId, OrderStatus orderStatus);

    void changeCarToRentStatus(Integer carToRentId);
}
