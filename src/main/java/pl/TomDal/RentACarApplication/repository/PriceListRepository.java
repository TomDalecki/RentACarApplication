package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.PriceList;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.repository.jpa.PriceListJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.PriceListEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.PriceListDAO;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriceListRepository implements PriceListDAO {
    private final PriceListJpaRepository priceListJpaRepository;
    private final PriceListEntityMapper priceListEntityMapper;

    @Override
    public Optional<PriceList> findLatestPriceByCarType(CarType carType) {
        return priceListJpaRepository.findLatestPriceByCarTypeOrderByPriceDateDesc(carType)
                .map(priceListEntityMapper::mapFromEntity);
    }
}
