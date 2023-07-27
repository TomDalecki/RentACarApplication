package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RentalOrderService {
    RentalOrderDTO rentalOrderDTO;
    CarToRentDAO carToRentDAO;

    public void saveRentalOrder(RentalOrder rentalOrder){
        rentalOrderDTO.saveRentalOrder(rentalOrder);
    }


}
