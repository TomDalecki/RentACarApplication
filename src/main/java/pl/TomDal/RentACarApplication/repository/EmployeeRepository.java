package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.Employee;
import pl.TomDal.RentACarApplication.repository.jpa.EmployeeJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.EmployeeEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.EmployeeDAO;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class EmployeeRepository implements EmployeeDAO {

    private final EmployeeJpaRepository employeeJpaRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Override
    public void saveEmployee(Employee employee) {
        employeeJpaRepository.save(employeeEntityMapper.mapToEntity(employee));
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) {
        return employeeJpaRepository.findByEmail(email)
                .map(employeeEntityMapper::mapFromEntity);
    }
}
