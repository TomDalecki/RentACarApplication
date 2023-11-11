package pl.TomDal.RentACarApplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.TomDal.RentACarApplication.domain.Price;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.PriceDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceCalculationServiceTest {

    @InjectMocks
    PriceCalculationService priceCalculationService;

    @Mock
    PriceDAO priceDAO;

    @Test
    void thatCalculateTotalPriceWorksCorrectly() {
        //given
        LocalDate rentalStartDate = LocalDate.now();
        LocalDate rentalEndDate = LocalDate.now().plusDays(5);
        CarType carType = CarType.SUV;
        Price price = Price.builder().price(BigDecimal.valueOf(250.00)).build();

        when(priceDAO.findLatestPriceByCarType(carType)).thenReturn(Optional.of(price));

        //when
        BigDecimal result = priceCalculationService.calculateTotalPriceService(rentalStartDate, rentalEndDate, carType);

        //then
        assertEquals(BigDecimal.valueOf(1500.00), result);
    }
}