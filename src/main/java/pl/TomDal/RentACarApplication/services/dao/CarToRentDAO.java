package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
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

    void saveCar(CarToRent carToRent);

    List<CarToRent> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate);

    void changeCarStatusByCarId(Integer carToRentId, CarStatus carStatus);

    List<CarToRent> findCarsToRentByCarStatus(CarStatus carStatus);

    Optional<CarToRent> findCarToRentByCarId(Integer carToRentId);

    void deleteCarByVin(String vin);
}
