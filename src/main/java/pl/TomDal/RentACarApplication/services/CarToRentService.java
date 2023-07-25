package pl.TomDal.RentACarApplication.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;

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
}
