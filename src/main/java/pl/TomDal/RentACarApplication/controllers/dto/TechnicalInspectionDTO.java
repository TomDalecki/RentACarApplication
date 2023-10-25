package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.*;
import pl.TomDal.RentACarApplication.domain.CarToRent;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TechnicalInspectionDTO {
    Integer technicalInspectionId;
    LocalDate inspectionExpiryDate;
    CarToRent carToRent;


}