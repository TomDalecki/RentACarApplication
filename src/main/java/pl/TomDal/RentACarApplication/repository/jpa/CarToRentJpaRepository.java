package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.CarToRentEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarToRentJpaRepository extends JpaRepository<CarToRentEntity, Integer> {

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

    @Query("""
            SELECT car
            FROM CarToRentEntity car
            WHERE car.carStatus = 'TO_RENT'
            AND NOT EXISTS (
            SELECT 1
            FROM RentalHistoryEntity rh
            WHERE rh.carToRent = car
            AND ((rh.rentalStartDate <= :endDate AND rh.rentalEndDate >= :startDate)
            OR (rh.rentalStartDate >= :startDate AND rh.rentalStartDate <= :endDate)))
            """)
    List<CarToRentEntity> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate);
}
