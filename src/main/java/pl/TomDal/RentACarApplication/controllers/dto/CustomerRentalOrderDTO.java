package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.time.LocalDate;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRentalOrderDTO {
    private String customerEmail;
    private LocalDate rentalStartDate;
    private LocalDate rentalEndDate;
    private Integer selectedCarToRentId;
    private CarType carType;
}
