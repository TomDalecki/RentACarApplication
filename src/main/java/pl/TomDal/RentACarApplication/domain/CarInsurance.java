package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.InsuranceType;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode(of = "insuranceNumber")
@ToString
public class CarInsurance {

    Integer carInsuranceId;
    InsuranceType insuranceCompany;
    InsuranceType insuranceType;
    String insuranceNumber;
    OffsetDateTime insuranceStartDate;
    OffsetDateTime insuranceEndDate;
    @ToString.Exclude
    CarToRent carToRent;
}
