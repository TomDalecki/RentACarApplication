package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.CustomerEntity;

import java.util.Optional;

@Repository
public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByEmail(String email);
}
