package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.Employee;

import java.util.Optional;

public interface EmployeeDAO {

    void saveEmployee(Employee employee);

    Optional<Employee> findEmployeeByEmail(String email);
}
