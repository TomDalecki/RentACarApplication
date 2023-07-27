package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.*;
import pl.TomDal.RentACarApplication.domain.RentalOrder;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarToRentDTO {
    Integer carToRentId;
    String vin;
    String carIdNumber;
    CarType carType;
    String brand;
    String model;
    Integer year;
    String color;
    CarStatus carStatus;

}
