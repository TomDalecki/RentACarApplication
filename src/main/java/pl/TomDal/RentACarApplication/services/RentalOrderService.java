package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.exceptions.NotFoundException;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDAO;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RentalOrderService {
    RentalOrderDAO rentalOrderDAO;
    EmployeeService employeeService;

    public void saveRentalOrder(RentalOrder rentalOrder){
        rentalOrderDAO.saveRentalOrder(rentalOrder);
    }

    public void changeOrderStatusByOrderId (Integer rentOrderId, OrderStatus orderStatus){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.findEmployeeByEmail(authentication.getName());
        rentalOrderDAO.changeOrderStatusByOrderId(rentOrderId, orderStatus, employee);
    }

    public List<RentalOrder> findOpenRentalOrdersByEmail (String email){
        return rentalOrderDAO.findOpenRentalOrdersByEmail(email);
    }

    public List<OrderAndCar> findOrdersByStatusJoinedWithCars(OrderStatus orderStatus) {
        return rentalOrderDAO.findOrdersByStatusJoinedWithCars(orderStatus);
    }

    public OrderAndCar findOrderByRentalOrderIdJoinedWithCar(String rentNumber) {
        return rentalOrderDAO.findOrderByRentalOrderNumberJoinedWithCar(rentNumber).orElseThrow(
                ()->new NotFoundException("Could not find the order with rental number: [%s]".formatted(rentNumber))
        );
    }

    public void changeRentalPeriodByOrderId(Integer rentalOrderId, LocalDate newRentalStartDate, LocalDate newRentalEndDate) {
        rentalOrderDAO.changeRentalPeriodByOrderId(rentalOrderId, newRentalStartDate, newRentalEndDate);
    }
}
