package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.EmployeeEntity;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Integer> {
}