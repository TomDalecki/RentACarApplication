package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.*;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceCompanies;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceType;

import java.time.LocalDate;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInsuranceDTO {

    Integer carInsuranceId;
    InsuranceCompanies insuranceCompany;
    InsuranceType insuranceType;
    String insuranceNumber;
    LocalDate insuranceStartDate;
    LocalDate insuranceEndDate;
    CarToRent carToRent;
}
