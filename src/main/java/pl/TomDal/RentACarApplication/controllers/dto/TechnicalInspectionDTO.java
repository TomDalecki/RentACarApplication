package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import pl.TomDal.RentACarApplication.domain.CarToRent;

import java.time.LocalDate;

@Value
@Builder
@EqualsAndHashCode
@ToString
public class TechnicalInspectionDTO {
    Integer technicalInspectionId;
    LocalDate inspectionExpiryDate;
    CarToRent carToRent;
}