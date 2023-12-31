package pl.TomDal.RentACarApplication.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;
import pl.TomDal.RentACarApplication.services.dao.TechnicalInspectionDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarToRentService {
    CarToRentDAO carToRentDAO;
    CarToRentMapper carToRentMapper;
    TechnicalInspectionDAO technicalInspectionDAO;

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

    public CarToRent findByCarIdNumber(String carIdNumber){
        Optional<CarToRent> car = carToRentDAO.findByCarIdNumber(carIdNumber);
        return car.orElseThrow(
                ()-> new EntityNotFoundException("Could not find the car with car license plate: [%s]".formatted(carIdNumber)));
    }

    public Optional<CarToRent> findByVin(String vin){
        return carToRentDAO.findByVin(vin);
    }

    @Transactional
    public void changeCarStatusByCarId(Integer carToRentId, CarStatus carStatus) {
        carToRentDAO.changeCarStatusByCarId(carToRentId, carStatus);
    }

    @Transactional
    public CarToRentDTO saveCar(CarToRentDTO carToRentDTO, LocalDate technicalInspectionDate) {
        carToRentDAO.saveCar(carToRentMapper.mapFromDTO(carToRentDTO));

        CarToRent car = carToRentDAO.findByVin(carToRentDTO.getVin()).orElseThrow(
                ()-> new EntityNotFoundException("Could not find the car with VIN: [%s]"
                        .formatted(carToRentDTO.getVin())));

        technicalInspectionDAO.saveTechnicalInspection(car.getCarToRentId(), technicalInspectionDate);

        return carToRentMapper.mapToDTO(car);
    }

    @Transactional
    public void updateTechnicalStatus(Integer carToRentId, CarStatus carStatus) {
        carToRentDAO.changeCarStatusByCarId(carToRentId,carStatus);
    }

    @Transactional
    public void deleteCarByVin(String vin) {
        carToRentDAO.deleteCarByVin(vin);
    }
}
