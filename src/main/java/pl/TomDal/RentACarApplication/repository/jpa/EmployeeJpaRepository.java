package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.EmployeeEntity;

import java.util.Optional;

@Repository
public interface EmployeeJpaRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByEmail(String email);
}
