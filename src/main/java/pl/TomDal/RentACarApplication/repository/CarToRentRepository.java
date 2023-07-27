package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.CarToRentEntity;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.repository.jpa.CarToRentJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.CarToRentEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CarToRentRepository implements CarToRentDAO {

    private final CarToRentEntityMapper carToRentEntityMapper;
    private final CarToRentJpaRepository carToRentJpaRepository;


    @Override
    public List<CarToRent> findCarsAvailableToRent() {
        return carToRentJpaRepository.findAvailableCarsToRent().stream()
                .map(carToRentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public List<CarToRent> findAvailableCarsByCarType(CarType carType) {
        return carToRentJpaRepository.findAvailableCarsByCarType(carType).stream()
                .map(carToRentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarToRent> findByCarIdNumber(String carIdNumber) {
        return carToRentJpaRepository.findByCarIdNumber(carIdNumber)
                .map(carToRentEntityMapper::mapFromEntity);
    }

    @Override
    public Optional<CarToRent> findByVin(String vin) {
        return carToRentJpaRepository.findByVin(vin)
                .map(carToRentEntityMapper::mapFromEntity);
    }

    @Override
    public List<CarToRent> findAllCars() {
        return carToRentJpaRepository.findAll().stream()
                .map(carToRentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void addCarToFleet(CarToRent car) {
        CarToRentEntity carToSave = carToRentEntityMapper.mapToEntity(car);
        carToRentJpaRepository.save(carToSave);
    }

    @Override
    public List<CarToRent> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate) {
        return carToRentJpaRepository.findAvailableCarsByStartEndDate(startDate, endDate).stream()
                .map(carToRentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }
}
