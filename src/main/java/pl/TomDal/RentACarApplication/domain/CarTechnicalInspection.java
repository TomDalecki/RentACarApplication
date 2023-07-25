package pl.TomDal.RentACarApplication.domain;

import lombok.*;

import java.time.OffsetDateTime;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class CarTechnicalInspection {

    Integer carTechnicalInspectionId;
    OffsetDateTime inspectionExpiryDate;
    CarToRent carToRent;
}