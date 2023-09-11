package pl.TomDal.RentACarApplication.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.TomDal.RentACarApplication.domain.CarInsurance;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceType;
import pl.TomDal.RentACarApplication.repository.mapper.CarInsuranceEntityMapper;
import pl.TomDal.RentACarApplication.services.dao.InsuranceDAO;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class InsuranceService {
    InsuranceDAO insuranceDAO;
    CarInsuranceEntityMapper carInsuranceEntityMapper;
    CarToRentService carToRentService;

    @Transactional
    public void saveInsurance(CarInsurance carInsurance) {
        insuranceDAO.saveInsurance(carInsuranceEntityMapper.mapToEntity(carInsurance));

        List<CarInsurance> carInsuranceList = insuranceDAO
                .findCarInsuranceByCarId(carInsurance.getCarToRent().getCarToRentId());

        if (carInsuranceList.size() > 1) {
            CarInsurance latestCarInsurance;
            if (carInsurance.getInsuranceType().equals(InsuranceType.OC)) {

                latestCarInsurance = carInsuranceList.stream()
                        .filter(insurance -> insurance.getInsuranceType().equals(InsuranceType.AC))
                        .max(Comparator.comparing(CarInsurance::getCarInsuranceId)).orElseThrow();

            } else {
                latestCarInsurance = carInsuranceList.stream()
                        .filter(insurance -> insurance.getInsuranceType().equals(InsuranceType.OC))
                        .max(Comparator.comparing(CarInsurance::getCarInsuranceId)).orElseThrow();

            }
            if (latestCarInsurance.getInsuranceEndDate().isAfter(LocalDate.now())) {
                changeCarStatus(carInsurance);
            }
        }
    }

    private void changeCarStatus(CarInsurance carInsurance) {
        carToRentService.changeCarStatusByCarId(carInsurance.getCarToRent().getCarToRentId(),
                CarStatus.TO_RENT);
    }
}
