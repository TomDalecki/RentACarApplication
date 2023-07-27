package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarToRentDAO {

    List<CarToRent> findAllCars();

    List<CarToRent> findCarsAvailableToRent();

    List<CarToRent> findAvailableCarsByCarType(CarType carType);

    Optional<CarToRent> findByCarIdNumber(String carIdNumber);

    Optional<CarToRent> findByVin(String vin);

    void addCarToFleet(CarToRent carToRent);

    List<CarToRent> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate);
}
