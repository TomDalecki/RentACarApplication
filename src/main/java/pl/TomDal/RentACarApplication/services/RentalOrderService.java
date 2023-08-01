package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDAO;

import java.util.List;

@Service
@AllArgsConstructor
public class RentalOrderService {
    RentalOrderDAO rentalOrderDAO;
    CarToRentDAO carToRentDAO;

    public void saveRentalOrder(RentalOrder rentalOrder){
        rentalOrderDAO.saveRentalOrder(rentalOrder);
    }

    public void changeOrderStatus (Integer rentOrderId, OrderStatus orderStatus, Integer carToRentId){
        rentalOrderDAO.changeOrderStatus(rentOrderId, orderStatus);

        if (orderStatus.equals(OrderStatus.ACCEPTED)){
            changeCarToRentStatus(carToRentId, CarStatus.RENTED);
        }
    }

    private void changeCarToRentStatus(Integer carToRentId, CarStatus carStatus) {
        rentalOrderDAO.changeCarToRentStatus(carToRentId, carStatus);
    }

    public List<RentalOrder> findOpenRentalOrdersByEmail (String email){
        List<RentalOrder> openRentalOrdersByEmail = rentalOrderDAO.findOpenRentalOrdersByEmail(email);
        for (RentalOrder rentalOrder : openRentalOrdersByEmail) {
            System.out.println("Open rental orders by email: " + rentalOrder);
        }
        return openRentalOrdersByEmail;
    }


}
