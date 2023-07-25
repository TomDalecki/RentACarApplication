package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;

@Repository
public interface RentalOrderJpaRepository extends JpaRepository<RentalOrderEntity, Integer> {
}
