package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.controllers.dto.EmployeeDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.EmployeeMapper;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.services.dao.EmployeeDAO;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {
    EmployeeDAO employeeDAO;
    EmployeeMapper employeeMapper;

    @Transactional
    public void saveEmployee(EmployeeDTO employeeDTO) {
        employeeDAO.saveEmployee(employeeMapper.mapFromDTO(employeeDTO));
    }

    Optional<Employee> findEmployeeByEmail(String email) {
        return employeeDAO.findEmployeeByEmail(email);
    }
}
