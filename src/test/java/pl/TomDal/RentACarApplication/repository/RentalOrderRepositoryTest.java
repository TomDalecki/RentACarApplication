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
import pl.TomDal.RentACarApplication.domain.RentalOrder;

import java.time.LocalDate;

import static pl.TomDal.RentACarApplication.util.TestDataFixtures.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "pl.TomDal.RentACarApplication")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class RentalOrderRepositoryTest {
    CustomerRepository customerRepository;
    CarToRentRepository carToRentRepository;
    EmployeeRepository employeeRepository;
    RentalOrderRepository rentalOrderRepository;

    @Test
    void thatUpdateRentalStartDateAndRentalEndDateByRentalOrderIdWorksCorrectly() {
        //given
        customerRepository.saveCustomer(someCustomer1());
        carToRentRepository.saveCar(testCar1());
        employeeRepository.saveEmployee(testEmployee1());
        RentalOrder rentalOrder = testRentalOrder1();
        rentalOrder = rentalOrder.withCustomer(customerRepository.findAll().stream().findFirst().orElseThrow())
                                 .withCarToRent(carToRentRepository.findAllCars().stream().findFirst().orElseThrow())
                                 .withEmployee(employeeRepository.findAll().stream().findFirst().orElseThrow());

        rentalOrderRepository.saveRentalOrder(rentalOrder);

        Integer rentalOrderId = rentalOrderRepository.findAll().stream().findFirst().orElseThrow().getRentalOrderId();
        LocalDate newRentalStartDate = LocalDate.now();
        LocalDate newRentalOrderEndDate = LocalDate.now();

        //when
        rentalOrderRepository.changeRentalPeriodByOrderId(rentalOrderId, newRentalStartDate, newRentalOrderEndDate);
        LocalDate resultStartDate = rentalOrderRepository.findAll().stream().findFirst().orElseThrow().getRentalStartDate();
        LocalDate resultEndDate = rentalOrderRepository.findAll().stream().findFirst().orElseThrow().getRentalEndDate();

        //then
        Assertions.assertEquals(LocalDate.now(), resultStartDate);
        Assertions.assertEquals(LocalDate.now(), resultEndDate);
    }

    @Test
    void thatUpdateOrderStatusByRentalOrderIdWorksCorrectly() {
        //given
        //when
        //then
    }

    @Test
    void thatFindingOpenRentalOrdersByEmailWorksCorrectly() {
        //given
        //when
        //then
    }

    @Test
    void thatFindingOrdersByStatusJoinedWithCarsWorksCorrectly() {
        //given
        //when
        //then
    }

    @Test
    void thatFindingOrderByRentalOrderIdJoinedWithCarWorksCorrectly() {
        //given
        //when
        //then
    }

}