package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.OrderAndCar;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalOrderJpaRepository extends JpaRepository<RentalOrderEntity, Integer> {
    @Transactional
    @Modifying
    @Query("""
            UPDATE RentalOrderEntity r
            SET r.orderStatus = :orderStatus
            WHERE r.rentalOrderId = :rentalOrderId""")
    void updateOrderStatusByRentalOrderId(@Param("orderStatus") OrderStatus orderStatus,
                                          @Param("rentalOrderId") Integer rentalOrderId);

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
    INNER JOIN CarToRentEntity car ON ord.rentalOrderId = car.carToRentId
    WHERE ord.orderStatus = :orderStatus
    """)
    List<OrderAndCar> findOrdersByStatusJoinedWithCars(@Param("orderStatus") OrderStatus orderStatus);

    @Query("""
    SELECT ord.rentalOrderId as rentalOrderId, car.carToRentId as carToRentId, car.carIdNumber as carIdNumber,
    car.carType as carType, car.brand as brand, car.model as model, car.year as year, car.color as color,
    ord.rentalStartDate as rentalStartDate, ord.rentalEndDate as rentalEndDate, ord.totalPrice as totalPrice
    FROM RentalOrderEntity ord
    INNER JOIN FETCH CarToRentEntity car ON ord.rentalOrderId = car.carToRentId
    WHERE ord.rentNumber = :rentNumber
    """)
    Optional<OrderAndCar> findOrderByRentalOrderIdJoinedWithCar(@Param("rentNumber")String rentNumber);
}
