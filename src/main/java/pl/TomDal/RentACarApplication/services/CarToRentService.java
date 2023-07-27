package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.CarToRent;
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
        List<CarToRent> allCars = carToRentDAO.findAllCars();
        for (CarToRent allCar : allCars) {
            System.out.println("All cars: " + allCar);
        }
        return allCars;
    }

    public List<CarToRent> findAvailableCars(){
        List<CarToRent> carsAvailableToRent = carToRentDAO.findCarsAvailableToRent();
        for (CarToRent carToRent : carsAvailableToRent) {
            System.out.println("Car to rent: " + carToRent);
        }
        return carsAvailableToRent;
    }

    public List<CarToRent> findAvailableCarsByCarType(CarType carType){
        List<CarToRent> availableCarsByCarType = carToRentDAO.findAvailableCarsByCarType(carType);
        for (CarToRent carToRent : availableCarsByCarType) {
            System.out.println("Cars to rent - by Type: " + carToRent);
        }
        return availableCarsByCarType;
    }

    public List<CarToRent> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate){
        List<CarToRent> findAvailableCarsByStartEndDate = carToRentDAO.findAvailableCarsByStartEndDate(startDate, endDate);
        for (CarToRent carToRent : findAvailableCarsByStartEndDate) {
            System.out.println("Cars to rent by date: "  + carToRent);
        }
        return findAvailableCarsByStartEndDate;
    }

    public Optional<CarToRent> findByCarIdNumber(String carIdNumber){
        Optional<CarToRent> car = carToRentDAO.findByCarIdNumber(carIdNumber);
        System.out.println("Car by IdNumber: " + car.get());
        return car;
    }
    public Optional<CarToRent> findByVin(String vin){
        Optional<CarToRent> car = carToRentDAO.findByVin(vin);
        System.out.println("Car by VIN: " + car.get());
        return car;
    }

    @Transactional
    public void addCar(CarToRent carToRent){
        carToRentDAO.addCar(carToRent);
    }
}
