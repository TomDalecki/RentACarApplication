package pl.TomDal.RentACarApplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;
import pl.TomDal.RentACarApplication.exceptions.NotFoundException;
import pl.TomDal.RentACarApplication.services.dao.RentalOrderDAO;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RentalOrderServiceTest {
    @InjectMocks
    RentalOrderService rentalOrderService;

    @Mock
    RentalOrderDAO rentalOrderDAO;

    @Mock
    EmployeeService employeeService;

    @Mock
    SecurityContext securityContext;

    @Mock
    Authentication authentication;

    @Mock
    OrderAndCar orderAndCar;

    @Test
    void thatSavingRentalOrderWorksCorrectly() {
        //given
        RentalOrder rentalOrder = TestDataFixtures.testRentalOrder1();

        //when
        rentalOrderService.saveRentalOrder(rentalOrder);

        //then
        verify(rentalOrderDAO).saveRentalOrder(rentalOrder);
        verify(rentalOrderDAO, times(1)).saveRentalOrder(rentalOrder);
    }

    @Test
    void thatChangingOrderStatusByOrderIdWorksCorrectly() {
        //given
        Integer rentalOrderId = 1;
        OrderStatus orderStatus = OrderStatus.NEW_ORDER;
        Employee employee = TestDataFixtures.testEmployee1();

        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn(employee.getEmail());
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(employeeService.findEmployeeByEmail(authentication.getName())).thenReturn(employee);

        //when
        rentalOrderService.changeOrderStatusByOrderId(rentalOrderId, orderStatus);

        //then
        verify(rentalOrderDAO).changeOrderStatusByOrderId(rentalOrderId, orderStatus, employee);
        verify(rentalOrderDAO, times(1)).changeOrderStatusByOrderId(rentalOrderId, orderStatus, employee);
    }

    @Test
    void thatFindingOpenRentalOrdersByEmailWorksCorrectly() {
        //given
        String email = TestDataFixtures.someCustomer1().getEmail();

        //when
        rentalOrderService.findOpenRentalOrdersByEmail(email);

        //then
        verify(rentalOrderDAO).findOpenRentalOrdersByEmail(email);
        verify(rentalOrderDAO, times(1)).findOpenRentalOrdersByEmail(email);
    }

    @Test
    void thatFindingOrdersByStatusJoinedWithCarsWorksCorrectly() {
        //given
        List<OrderAndCar> expectedOrdersList = new ArrayList<>();
        expectedOrdersList.add(orderAndCar);
        OrderStatus orderStatus = OrderStatus.NEW_ORDER;
        when(rentalOrderDAO.findOrdersByStatusJoinedWithCars(orderStatus)).thenReturn(expectedOrdersList);

        //when
        List<OrderAndCar> result = rentalOrderService.findOrdersByStatusJoinedWithCars(orderStatus);

        //then
        assertEquals(expectedOrdersList.size(), result.size());
        verify(rentalOrderDAO, times(1)).findOrdersByStatusJoinedWithCars(orderStatus);

    }

    @Test
    void thatFindingOrderByRentalOrderIdJoinedWithCarWorksCorrectly() {
        //given
        String rentNumber = "someRentNumber";
        when(rentalOrderDAO.findOrderByRentalOrderNumberJoinedWithCar(rentNumber)).thenReturn(Optional.of(orderAndCar));

        //when
        OrderAndCar result = rentalOrderService.findOrderByRentalOrderIdJoinedWithCar(rentNumber);

        //then
        assertEquals(orderAndCar, result);
        verify(rentalOrderDAO, times(1)).findOrderByRentalOrderNumberJoinedWithCar(rentNumber);
    }

    @Test
    void thatFindOrderByRentalOrderIdJoinedWithCar_withNotExistingRentalOrderId() {
        //given
        String rentNumber = "someNotExistingRentNumber";
        String expectedMessage = "Could not find the order with rental number: [%s]".formatted(rentNumber);
        when(rentalOrderDAO.findOrderByRentalOrderNumberJoinedWithCar(rentNumber)).thenReturn(Optional.empty());

        //when then
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> rentalOrderService.findOrderByRentalOrderIdJoinedWithCar(rentNumber));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        verify(rentalOrderDAO, times(1)).findOrderByRentalOrderNumberJoinedWithCar(rentNumber);
    }

    @Test
    void thatChangingRentalPeriodByOrderIdWorksCorrectly() {
        //given
        Integer rentalOrderId = 1;
        LocalDate newRentalStartDate = LocalDate.now();
        LocalDate newRentalEndDate = LocalDate.now().plusDays(1);

        //when
        rentalOrderService.changeRentalPeriodByOrderId(rentalOrderId, newRentalStartDate, newRentalEndDate);

        //then
        verify(rentalOrderDAO, times(1))
                .changeRentalPeriodByOrderId(rentalOrderId, newRentalStartDate, newRentalEndDate);

    }
}