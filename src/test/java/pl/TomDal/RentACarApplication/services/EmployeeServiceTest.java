package pl.TomDal.RentACarApplication.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.TomDal.RentACarApplication.controllers.dto.EmployeeDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.EmployeeMapper;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.exceptions.NotFoundException;
import pl.TomDal.RentACarApplication.services.dao.EmployeeDAO;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeDAO employeeDAO;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void thatFindingEmployeeByEmailWorksCorrectly() {
        //given
        Employee expectedEmployee = TestDataFixtures.testEmployee1();
        String email = TestDataFixtures.testEmployee1().getEmail();
        when(employeeDAO.findEmployeeByEmail(email)).thenReturn(Optional.ofNullable(expectedEmployee));

        //when
        Employee result = employeeService.findEmployeeByEmail(email);

        //then
        assertNotNull(expectedEmployee);
        assertEquals(expectedEmployee.getSurname(), result.getSurname());
        assertEquals(expectedEmployee.getPesel(), result.getPesel());

        verify(employeeDAO, Mockito.times(1)).findEmployeeByEmail(email);
    }

    @Test
    void thatFindingEmployeeByNotExistingEmailThrowsException() {
        //given
        String email = "NotExistingEmail";
        String expectedMessage = "Could not find the employee with email: [%s]".formatted(email);

        //when
        when(employeeDAO.findEmployeeByEmail(email)).thenReturn(Optional.empty());

        //then
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> employeeService.findEmployeeByEmail(email));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        verify(employeeDAO, Mockito.times(1)).findEmployeeByEmail(email);
    }

    @Test
    void thatSavingEmployeeWorksCorrectly() {
        //given
        Employee employee = TestDataFixtures.testEmployee1();
        EmployeeDTO employeeDTO = TestDtoDataFixtures.testEmployeeDTO1();
        when(employeeMapper.mapFromDTO(employeeDTO)).thenReturn(employee);

        //when
        employeeService.saveEmployee(employeeDTO);

        //then
        verify(employeeMapper).mapFromDTO(employeeDTO);
        verify(employeeDAO, times(1)).saveEmployee(employee);
    }
}