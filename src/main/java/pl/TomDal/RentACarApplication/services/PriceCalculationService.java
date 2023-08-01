package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.PriceListDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class PriceCalculationService {
    PriceListDAO priceListDAO;

    public BigDecimal calculateTotalPriceService(
            LocalDate rentalStartDate, LocalDate rentalEndDate, CarType carType){

        long daysBetween = ChronoUnit.DAYS.between(rentalStartDate, rentalEndDate) + 1;
        BigDecimal price = priceListDAO.findLatestPriceByCarType(carType).orElseThrow().getPrice();
        return price.multiply(BigDecimal.valueOf(daysBetween));
    }
}
