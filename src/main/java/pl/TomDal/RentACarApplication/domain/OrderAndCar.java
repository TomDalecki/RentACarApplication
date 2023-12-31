package pl.TomDal.RentACarApplication.domain;

import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderAndCar {
    Integer getRentalOrderId();
    String getCarIdNumber();
    CarType getCarType();
    String getBrand();
    String getModel();
    Integer getYear();
    String getColor();
    LocalDate getRentalStartDate();
    LocalDate getRentalEndDate();
    BigDecimal getTotalPrice();
    Integer getCarToRent();
}
