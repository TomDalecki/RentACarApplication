package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.PriceDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class PriceCalculationService {
    PriceDAO priceDAO;

    public BigDecimal calculateTotalPriceService(
            LocalDate rentalStartDate, LocalDate rentalEndDate, CarType carType){

        long daysBetween = ChronoUnit.DAYS.between(rentalStartDate, rentalEndDate) + 1;
        BigDecimal price = priceDAO.findLatestPriceByCarType(carType).orElseThrow().getPrice();
        return price.multiply(BigDecimal.valueOf(daysBetween));
    }
}
