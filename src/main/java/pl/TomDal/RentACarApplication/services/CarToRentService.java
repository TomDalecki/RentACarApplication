package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarToRentService {
    CarToRentDAO carToRentDAO;

    public List<CarToRent> findAllCars(){
        return carToRentDAO.findAllCars();
    }

    public List<CarToRent> findAvailableCars(){
        return carToRentDAO.findCarsAvailableToRent();
    }

    public List<CarToRent> findCarsToRentByCarStatus(CarStatus carStatus) {
        return carToRentDAO.findCarsToRentByCarStatus(carStatus);
    }

    public List<CarToRent> findAvailableCarsByCarType(CarType carType){
        return carToRentDAO.findAvailableCarsByCarType(carType);
    }

    public List<CarToRent> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate){
        return carToRentDAO.findAvailableCarsByStartEndDate(startDate, endDate);
    }
    public Optional<CarToRent> findByCarIdNumber(String carIdNumber){
        Optional<CarToRent> car = carToRentDAO.findByCarIdNumber(carIdNumber);
        System.out.println("Car by IdNumber: " + car.orElseThrow());
        return car;
    }

    public Optional<CarToRent> findByVin(String vin){
        Optional<CarToRent> car = carToRentDAO.findByVin(vin);
        System.out.println("Car by VIN: " + car.orElseThrow());
        return car;
    }

    @Transactional
    public void addCarToFleet(CarToRent carToRent){
        carToRentDAO.addCarToFleet(carToRent);
    }

    @Transactional
    public void changeCarStatusByCarId(Integer reservedCarId, CarStatus carStatus) {
        carToRentDAO.changeCarStatusByCarId(reservedCarId, carStatus);
    }
}
