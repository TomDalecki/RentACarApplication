package pl.TomDal.RentACarApplication.domain;

import lombok.*;

import java.time.LocalDate;

@Setter
@Data
@Builder
@EqualsAndHashCode
@ToString
public class TechnicalInspection {

    Integer technicalInspectionId;
    LocalDate inspectionExpiryDate;
    CarToRent carToRent;
}