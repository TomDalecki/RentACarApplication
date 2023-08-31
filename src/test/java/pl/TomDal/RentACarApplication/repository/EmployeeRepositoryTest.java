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
import pl.TomDal.RentACarApplication.domain.Employee;

import java.util.List;
import java.util.NoSuchElementException;

import static pl.TomDal.RentACarApplication.util.TestDataFixtures.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "pl.TomDal.RentACarApplication")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class EmployeeRepositoryTest {

    EmployeeRepository employeeRepository;

    @Test
    void thatEmployeeCanBeSavedCorrectly() {
        //given
        Employee employee1 = testEmployee1();
        Employee employee2 = testEmployee2();
        Employee employee3 = testEmployee3();

        //when
        employeeRepository.saveEmployee(employee1);
        employeeRepository.saveEmployee(employee2);
        employeeRepository.saveEmployee(employee3);
        List<Employee> result = employeeRepository.findAll();

        //then
        Assertions.assertEquals(result.size(), 3);
    }

    @Test
    void thatEmployeeCanBeFindByEmail() {
        //given
        employeeRepository.saveEmployee(testEmployee1());
        employeeRepository.saveEmployee(testEmployee2());
        employeeRepository.saveEmployee(testEmployee3());

        //when
        Employee employee = employeeRepository.findEmployeeByEmail(testEmployee3().getEmail())
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        org.assertj.core.api.Assertions.assertThat(employee.getPesel()).isEqualTo(testEmployee3().getPesel());
    }

    @Test
    void thatThrowExceptionWhenEmployeeNotFound() {
        // Given
        employeeRepository.saveEmployee(testEmployee1());
        employeeRepository.saveEmployee(testEmployee2());
        employeeRepository.saveEmployee(testEmployee3());
        String nonExistentEmail = "fakeEmail@com";

        // When, Then
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
            employeeRepository.findEmployeeByEmail(nonExistentEmail)
                    .orElseThrow(() -> new NoSuchElementException("Employee not found"));
        }).isInstanceOf(NoSuchElementException.class);
    }
}