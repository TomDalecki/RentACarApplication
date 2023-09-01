package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.repository.mapper.EmployeeEntityMapper;

import java.time.LocalDate;
import java.util.List;

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
    EmployeeEntityMapper employeeEntityMapper;
    RentalOrderRepository rentalOrderRepository;

    @BeforeEach
    public void setup(){
        customerRepository.saveCustomer(someCustomer1());
        customerRepository.saveCustomer(someCustomer2());
        customerRepository.saveCustomer(someCustomer3());
        carToRentRepository.saveCar(testCar1());
        carToRentRepository.saveCar(testCar2());
        carToRentRepository.saveCar(testCar3());
        employeeRepository.saveEmployee(testEmployee1());
        employeeRepository.saveEmployee(testEmployee2());
        employeeRepository.saveEmployee(testEmployee3());

        RentalOrder rentalOrder = testRentalOrder1();
        rentalOrder = rentalOrder.withCustomer(customerRepository.findAll().stream().findFirst().orElseThrow())
                .withCarToRent(carToRentRepository.findAllCars().stream().findFirst().orElseThrow())
                .withEmployee(employeeRepository.findAll().stream().findFirst().orElseThrow());
        rentalOrderRepository.saveRentalOrder(rentalOrder);
    }

    @Test
    void thatUpdateRentalStartDateAndRentalEndDateByRentalOrderIdWorksCorrectly() {
        //given
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
        Integer rentalOrderId = rentalOrderRepository.findAll().stream().findFirst().orElseThrow().getRentalOrderId();
        OrderStatus orderStatus = OrderStatus.ACCEPTED;
        Employee employee = employeeRepository.findEmployeeByEmail("testEmpl2@pl").orElseThrow();

        //when
        rentalOrderRepository.changeOrderStatusByOrderId(rentalOrderId, orderStatus, employee);
        OrderStatus result = rentalOrderRepository.findRentalOrderById(rentalOrderId).orElseThrow().getOrderStatus();

        //then
        Assertions.assertEquals(OrderStatus.ACCEPTED, result);
    }

    @Test
    void thatFindingOpenRentalOrdersByEmailWorksCorrectly() {
        //given
        String email = someCustomer1().getEmail();

        //when
        List<RentalOrder> result = rentalOrderRepository.findOpenRentalOrdersByEmail(email);

        //then
        Assertions.assertEquals(1, result.size());

    }

    @Test
    void thatFindingOrdersByStatusJoinedWithCarsWorksCorrectly() {
        //given
        OrderStatus orderStatus = OrderStatus.NEW_ORDER;

        //when
        List<OrderAndCar> result = rentalOrderRepository.findOrdersByStatusJoinedWithCars(orderStatus);

        //then
        Assertions.assertEquals(1, result.size());
    }

    @Test
    void thatFindingOrderByRentalOrderNumberJoinedWithCarWorksCorrectly() {
        //given
        String rentalNumber = rentalOrderRepository.findAll().stream().findFirst().orElseThrow().getRentNumber();

        //when
        OrderAndCar result = rentalOrderRepository.findOrderByRentalOrderNumberJoinedWithCar(rentalNumber).orElseThrow();

        //then
        Assertions.assertNotNull(result);
    }
}