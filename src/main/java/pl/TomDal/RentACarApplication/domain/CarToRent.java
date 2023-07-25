package pl.TomDal.RentACarApplication.domain;

import lombok.*;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.Set;

@With
@Value
@Builder
@EqualsAndHashCode
@ToString
public class CarToRent {
    Integer carToRentId;
    String vin;
    String carIdNumber;
    CarType carType;
    String brand;
    String model;
    Integer year;
    String color;
    CarStatus carStatus;
    @ToString.Exclude
    Set<RentalOrder> rentalOrders;

}
