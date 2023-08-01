package pl.TomDal.RentACarApplication.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.entity.PriceListEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Optional;

@Repository
public interface PriceListJpaRepository extends JpaRepository<PriceListEntity, Integer> {

    @Query("""
            SELECT p FROM PriceListEntity p
            WHERE p.carType = :carType ORDER BY p.priceDate DESC
            LIMIT 1""")
    Optional<PriceListEntity> findLatestPriceByCarTypeOrderByPriceDateDesc(@Param("carType") CarType carType);
}
