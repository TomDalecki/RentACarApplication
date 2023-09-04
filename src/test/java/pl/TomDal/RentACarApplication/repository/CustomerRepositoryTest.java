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
import pl.TomDal.RentACarApplication.domain.Customer;

import java.util.List;
import java.util.NoSuchElementException;

import static pl.TomDal.RentACarApplication.util.TestDataFixtures.*;

@ActiveProfiles("test")
//@SpringBootTest
@DataJpaTest
@ComponentScan(basePackages = "pl.TomDal.RentACarApplication")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CustomerRepositoryTest {
    private CustomerRepository customerRepository;

    @Test
    void thatCustomerCanBeSavedCorrectly() {
        //given
        Customer customer1 = someCustomer1();
        Customer customer2 = someCustomer2();
        Customer customer3 = someCustomer3();

        //when
        customerRepository.saveCustomer(customer1);
        customerRepository.saveCustomer(customer2);
        customerRepository.saveCustomer(customer3);
        List<Customer> result = customerRepository.findAll();

        //then
        Assertions.assertEquals(result.size(), 3);
    }

    @Test
    void thatCustomerCanBeFindByEmail() {
        //given
        customerRepository.saveCustomer(someCustomer1());
        customerRepository.saveCustomer(someCustomer2());
        customerRepository.saveCustomer(someCustomer3());

        //when
        Customer customer = customerRepository.findCustomerByEmail(someCustomer2().getEmail())
                .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        org.assertj.core.api.Assertions.assertThat(customer.getPhone()).isEqualTo(someCustomer2().getPhone());
    }

    @Test
    void thatThrowExceptionWhenCCustomerNotFound() {
        // Given
        customerRepository.saveCustomer(someCustomer1());
        customerRepository.saveCustomer(someCustomer2());
        customerRepository.saveCustomer(someCustomer3());
        String nonExistentEmail = "fakeEmail@com";

        // When, Then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            customerRepository.findCustomerByEmail(nonExistentEmail)
                    .orElseThrow(() -> new NoSuchElementException("Customer not found"));
        }).isInstanceOf(NoSuchElementException.class);
    }
}