package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.TomDal.RentACarApplication.domain.Price;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import static pl.TomDal.RentACarApplication.util.TestDataFixtures.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "pl.TomDal.RentACarApplication")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class PriceRepositoryTest {
    private final PriceRepository priceRepository;

    @Test
    void thatPriceIsSavedCorrectly() {
        //given
        Price price = testPrice1();

        //when
        priceRepository.saveNewPrice(price);
        List<Price> result = priceRepository.findAll();

        //then
        Assertions.assertEquals(1, result.size());
    }


    @Test
    void thatLatestPriceIsFindCorrectly() {
        //given
        Price price1 = testPrice1();
        Price price2 = testPrice2();
        Price price3 = testPrice3();
        Price price4 = testPrice4();
        CarType carType = CarType.Sedan;
        priceRepository.saveNewPrice(price1);
        priceRepository.saveNewPrice(price2);
        priceRepository.saveNewPrice(price3);
        priceRepository.saveNewPrice(price4);

        //when
        BigDecimal priceResult = priceRepository.findLatestPriceByCarType(carType)
                .orElseThrow(() ->
                        new NoSuchElementException("No price for car type: [%s] in data base".formatted(carType)))
                .getPrice();

        //then
        Assertions.assertEquals(BigDecimal.valueOf(100.00), priceResult);
    }
}