package pl.TomDal.RentACarApplication.services.dao;

import pl.TomDal.RentACarApplication.domain.CarInsurance;
import pl.TomDal.RentACarApplication.entity.CarInsuranceEntity;

import java.util.List;

public interface InsuranceDAO {
    void saveInsurance(CarInsuranceEntity carInsuranceEntity);

    List<CarInsurance> findCarInsuranceByCarId(Integer carToRentId);
}
