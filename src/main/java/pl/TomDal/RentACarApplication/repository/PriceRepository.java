package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.Price;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.repository.jpa.PriceJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.PriceEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.PriceDAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PriceRepository implements PriceDAO {
    private final PriceJpaRepository priceJpaRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Override
    public Optional<Price> findLatestPriceByCarType(CarType carType) {
        return priceJpaRepository.findLatestPriceByCarTypeOrderByPriceDateDesc(carType)
                .map(priceEntityMapper::mapFromEntity);
    }

    public void saveNewPrice(Price price) {
        priceJpaRepository.save(priceEntityMapper.mapToEntity(price));
    }

    public List<Price> findAll() {
        return priceJpaRepository.findAll().stream()
                .map(priceEntityMapper::mapFromEntity).collect(Collectors.toList());
    }
}
