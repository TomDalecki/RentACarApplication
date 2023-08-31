package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.PriceEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Integer> {

    @Query("""
            SELECT p FROM PriceEntity p
            WHERE p.carType = :carType ORDER BY p.priceDate DESC
            LIMIT 1""")
    Optional<PriceEntity> findLatestPriceByCarTypeOrderByPriceDateDesc(@Param("carType") CarType carType);
}
