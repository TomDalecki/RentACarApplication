package pl.TomDal.RentACarApplication.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@EqualsAndHashCode
@ToString
public class TechnicalInspection {

    Integer technicalInspectionId;
    LocalDate inspectionExpiryDate;
    CarToRent carToRent;
}