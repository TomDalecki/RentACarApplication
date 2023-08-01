package pl.TomDal.RentACarApplication.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

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
