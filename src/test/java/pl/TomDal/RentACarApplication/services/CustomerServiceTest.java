package pl.TomDal.RentACarApplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CustomerMapper;
import pl.TomDal.RentACarApplication.domain.Customer;
import pl.TomDal.RentACarApplication.exceptions.NotFoundException;
import pl.TomDal.RentACarApplication.services.dao.CustomerDAO;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerDAO customerDAO;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void thatFindingCustomerByEmailWorksCorrectly() {
        //given
        Customer expectedCustomer = TestDataFixtures.someCustomer1();
        String email = TestDataFixtures.someCustomer1().getEmail();
        when(customerDAO.findCustomerByEmail(email)).thenReturn(Optional.ofNullable(expectedCustomer));

        //when
        Customer result = customerService.findCustomerByEmail(email);

        //then
        assertNotNull(expectedCustomer);
        assertEquals(expectedCustomer.getSurname(), result.getSurname());
        assertEquals(expectedCustomer.getPhone(), result.getPhone());

        verify(customerDAO, Mockito.times(1)).findCustomerByEmail(email);
    }

    @Test
    void thatFindingCustomerByNotExistingEmailThrowsException() {
        //given
        String email = "NotExistingEmail";
        String expectedMessage = "Could not find customer with email: [%s]".formatted(email);

        //when
        when(customerDAO.findCustomerByEmail(email)).thenReturn(Optional.empty());

        //then
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> customerService.findCustomerByEmail(email));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        verify(customerDAO, Mockito.times(1)).findCustomerByEmail(email);
    }

    @Test
    void thatSavingCustomerWorksCorrectly() {
        //given
        Customer customer = TestDataFixtures.someCustomer1();
        CustomerDTO customerDTO = TestDtoDataFixtures.someCustomerDTO1();
        when(customerMapper.mapFromDTO(customerDTO)).thenReturn(customer);

        //when
        customerService.saveCustomer(customerDTO);

        //then
        verify(customerMapper).mapFromDTO(customerDTO);
        verify(customerDAO, times(1)).saveCustomer(customer);
    }
}