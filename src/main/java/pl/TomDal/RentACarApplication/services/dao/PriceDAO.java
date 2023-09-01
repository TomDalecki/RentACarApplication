package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.Price;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Optional;

public interface PriceDAO {

    Optional<Price> findLatestPriceByCarType(CarType carType);
}
