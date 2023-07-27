package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.entity.RentalOrderEntity;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.util.Collection;
import java.util.List;

@Repository
public interface RentalOrderJpaRepository extends JpaRepository<RentalOrderEntity, Integer> {
    @Transactional
    @Modifying
    @Query("update RentalOrderEntity r set r.orderStatus = :orderStatus where r.rentalOrderId = :rentalOrderId")
    void updateOrderStatusByRentalOrderId(@Param("orderStatus") OrderStatus orderStatus,
                                          @Param("rentalOrderId") Integer rentalOrderId);


    @Query("""
            SELECT ord FROM RentalOrderEntity ord
            JOIN FETCH ord.customer customer
            WHERE ord.orderStatus = 'NEW_ORDER' AND customer.email = :email
            """)
    List<RentalOrderEntity> findOpenRentalOrdersByEmail(String email);
}
