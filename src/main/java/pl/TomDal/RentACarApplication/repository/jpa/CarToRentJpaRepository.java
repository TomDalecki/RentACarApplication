package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.entity.CarToRentEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
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
            WHERE car.carStatus = :carStatus
            """)
    List<CarToRentEntity> findCarsToRentByCarStatus(CarStatus carStatus);

    //To jest do usunięcia i zastąpienia przez query powyżej
    @Query("""
            SELECT car FROM CarToRentEntity car
            WHERE car.carStatus = 'TO_RENT'
            """)
    List<CarToRentEntity> findAvailableCarsToRent();

    @Query("""
            SELECT car FROM CarToRentEntity car
            WHERE car.carType = ?1 AND car.carStatus = 'TO_RENT'
            """)
    List<CarToRentEntity> findAvailableCarsByCarType(CarType carType);

    @Query(value = """
                SELECT car.* FROM car_to_rent car
                WHERE car.car_status = 'TO_RENT'
                AND NOT EXISTS (
                SELECT 1 FROM rental_order ro
                WHERE ro.car_to_rent_id = car.car_to_rent_id
                AND ((ro.order_status <> 'CANCELED')
                AND ((:startDate >= ro.rental_start_date AND :endDate <= ro.rental_end_date)
                OR (:startDate <= ro.rental_start_date AND :endDate >= ro.rental_start_date))))
                  """, nativeQuery = true)
    List<CarToRentEntity> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate);

    @Transactional
    @Modifying
    @Query("""
            UPDATE CarToRentEntity c
            SET c.carStatus = :carStatus
            WHERE c.carToRentId = :carToRentId
            """)
    void updateCarStatusByCarToRentId(@Param("carToRentId") Integer carToRentId,
                                      @Param("carStatus")CarStatus carStatus);
}