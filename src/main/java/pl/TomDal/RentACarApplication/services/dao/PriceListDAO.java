package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.PriceList;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Optional;

public interface PriceListDAO {

    Optional<PriceList> findLatestPriceByCarType(CarType carType);
}
