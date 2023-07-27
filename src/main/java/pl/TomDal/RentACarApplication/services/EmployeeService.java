package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    RentalOrderDTO rentalOrderDTO;

    public List<RentalOrder> findOpenRentalOrdersByEmail (String email){
        List<RentalOrder> openRentalOrdersByEmail = rentalOrderDTO.findOpenRentalOrdersByEmail(email);
        for (RentalOrder rentalOrder : openRentalOrdersByEmail) {
            System.out.println("Open rental orders by email: " + rentalOrder);
        }
        return openRentalOrdersByEmail;
    }

    public void changeOrderStatus (Integer rentOrderId, OrderStatus orderStatus, Integer carToRentId){
        rentalOrderDTO.changeOrderStatus(rentOrderId, orderStatus);

        if (orderStatus.equals(OrderStatus.ACCEPTED)){
            changeCarToRentStatus(carToRentId);
        }
    }

    private void changeCarToRentStatus(Integer carToRentId) {
        rentalOrderDTO.changeCarToRentStatus(carToRentId);
    }

}
