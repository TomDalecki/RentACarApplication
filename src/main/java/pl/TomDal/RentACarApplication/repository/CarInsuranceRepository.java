package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.TomDal.RentACarApplication.domain.CarInsurance;
import pl.TomDal.RentACarApplication.entity.CarInsuranceEntity;
import pl.TomDal.RentACarApplication.repository.jpa.CarInsuranceJpaRepository;
import pl.TomDal.RentACarApplication.repository.mapper.CarInsuranceEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.InsuranceDAO;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CarInsuranceRepository implements InsuranceDAO {
    CarInsuranceJpaRepository carInsuranceJpaRepository;
    CarInsuranceEntityMapper carInsuranceEntityMapper;

    @Override
    public void saveInsurance(CarInsuranceEntity carInsuranceEntity) {
        carInsuranceJpaRepository.save(carInsuranceEntity);
    }

    @Override
    public List<CarInsurance> findCarInsuranceByCarId(Integer carToRentId) {
        return carInsuranceJpaRepository.findByCarToRent_CarToRentId(carToRentId)
                .stream().map(carInsuranceEntityMapper::mapFromEntity).collect(Collectors.toList());
    }
}
