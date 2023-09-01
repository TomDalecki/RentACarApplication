package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalOrderDAO {
    void saveRentalOrder(RentalOrder rentalOrder);

    List<RentalOrder> findOpenRentalOrdersByEmail(String email);

    void changeOrderStatusByOrderId(Integer rentOrderId, OrderStatus orderStatus, Employee employee);

    List<OrderAndCar> findOrdersByStatusJoinedWithCars(OrderStatus orderStatus);

    Optional<OrderAndCar> findOrderByRentalOrderNumberJoinedWithCar(String rentNumber);

    void changeRentalPeriodByOrderId(Integer rentalOrderId, LocalDate newRentalStartDate, LocalDate newRentalEndDate);
}
