package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.entity.EmployeeEntity;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalOrderJpaRepository extends JpaRepository<RentalOrderEntity, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE RentalOrderEntity r SET r.rentalStartDate = ?1, r.rentalEndDate = ?2 where r.rentalOrderId = ?3")
    void updateRentalStartDateAndRentalEndDateByRentalOrderId(LocalDate rentalStartDate,
                                                             LocalDate rentalEndDate, Integer rentalOrderId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE RentalOrderEntity r
            SET r.orderStatus = :orderStatus, r.employee = :employeeEntity
            WHERE r.rentalOrderId = :rentalOrderId""")
    void updateOrderStatusByRentalOrderId(@Param("orderStatus") OrderStatus orderStatus,
                                          @Param("rentalOrderId") Integer rentalOrderId,
                                          @Param("employeeEntity") EmployeeEntity employeeEntity);

    @Query("""
            SELECT ord FROM RentalOrderEntity ord
            JOIN FETCH ord.customer customer
            WHERE ord.orderStatus = 'NEW_ORDER' AND customer.email = :email
            """)
    List<RentalOrderEntity> findOpenRentalOrdersByEmail(String email);

    @Query("""
            SELECT ord.rentalOrderId as rentalOrderId, car.carToRentId as carToRentId, car.carIdNumber as carIdNumber,
            car.carType as carType, car.brand as brand, car.model as model, car.year as year, car.color as color,
            ord.rentalStartDate as rentalStartDate, ord.rentalEndDate as rentalEndDate, ord.totalPrice as totalPrice
            FROM RentalOrderEntity ord
            INNER JOIN CarToRentEntity car ON ord.carToRent.carToRentId = car.carToRentId
            WHERE ord.orderStatus = :orderStatus
            """)
    List<OrderAndCar> findOrdersByStatusJoinedWithCars(@Param("orderStatus") OrderStatus orderStatus);

    @Query(value = """
            SELECT ord.rental_order_id AS rentalOrderId,
            car.car_id_number AS carIdNumber,
            car.car_type AS carType,
            car.brand AS brand,
            car.model AS model,
            car.production_year AS year,
            car.color AS color,
            ord.rental_start_date AS rentalStartDate,
            ord.rental_end_date AS rentalEndDate,
            ord.total_price AS totalPrice,
            car.car_to_rent_id AS carToRentId
            FROM rental_order ord
            INNER JOIN car_to_rent car ON ord.car_to_rent_id = car.car_to_rent_id
            WHERE ord.rental_number = :rentNumber
            """, nativeQuery = true)
    Optional<OrderAndCar> findOrderByRentalOrderIdJoinedWithCar(@Param("rentNumber")String rentNumber);
}