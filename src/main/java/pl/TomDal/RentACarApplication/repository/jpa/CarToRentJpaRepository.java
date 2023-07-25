package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.CarToRentEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarToRentJpaRepository extends JpaRepository<CarToRentEntity, Integer> {

    List<CarToRentEntity> findAll();

    Optional<CarToRentEntity> findByCarIdNumber(String carIdNumber);

    Optional<CarToRentEntity> findByVin(String vin);

    @Query("""
            SELECT car FROM CarToRentEntity car
            WHERE car.carStatus = 'TO_RENT'
            """)
    List<CarToRentEntity> findAvailableCarsToRent();

    @Query("""
            SELECT car FROM CarToRentEntity car
            WHERE car.carType = ?1 and car.carStatus = 'TO_RENT'
            """)
    List<CarToRentEntity> findAvailableCarsByCarType(CarType carType);
}
