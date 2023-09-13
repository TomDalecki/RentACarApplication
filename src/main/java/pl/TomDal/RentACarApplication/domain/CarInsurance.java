package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceCompanies;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceType;

import java.time.LocalDate;

@With
@Value
@Builder
@EqualsAndHashCode(of = "insuranceNumber")
@ToString
public class CarInsurance {

    Integer carInsuranceId;
    InsuranceCompanies insuranceCompany;
    InsuranceType insuranceType;
    String insuranceNumber;
    LocalDate insuranceStartDate;
    LocalDate insuranceEndDate;
    CarToRent carToRent;
}
