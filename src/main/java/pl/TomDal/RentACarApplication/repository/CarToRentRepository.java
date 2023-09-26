package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
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
    public List<CarToRent> findAvailableCarsByStartEndDate(LocalDate startDate, LocalDate endDate) {
        return carToRentJpaRepository.findAvailableCarsByStartEndDate(startDate, endDate).stream()
                .map(carToRentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarToRent> findCarsToRentByCarStatus(CarStatus carStatus) {
        return carToRentJpaRepository.findCarsToRentByCarStatus(carStatus).stream()
                .map(carToRentEntityMapper::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CarToRent> findCarToRentByCarId(Integer carToRentId) {
        return Optional.ofNullable(carToRentEntityMapper
                .mapFromEntity(carToRentJpaRepository.findByCarToRentId(carToRentId)));
    }

    @Override
    public void deleteCarByVin(String vin) {
        carToRentJpaRepository.deleteByVin(vin);
    }

    @Override
    @Transactional
    public void changeCarStatusByCarId(Integer carToRentId, CarStatus carStatus) {
        carToRentJpaRepository.updateCarStatusByCarToRentId(carToRentId, carStatus);
    }

    @Override
    @Transactional
    public void saveCar(CarToRent car) {
        carToRentJpaRepository.save(carToRentEntityMapper.mapToEntity(car));
    }

    public void deleteCar(CarToRent car){
        carToRentJpaRepository.deleteByVin(car.getVin());
    }
}
