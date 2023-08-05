package pl.TomDal.RentACarApplication.domain;

import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface OrderAndCar {
    Integer getRentalOrderId();
    Integer getCarToRentId();
    String getCarIdNumber();
    CarType getCarType();
    String getBrand();
    String getModel();
    Integer getYear();
    String getColor();
    LocalDate getRentalStartDate();
    LocalDate getRentalEndDate();
    BigDecimal getTotalPrice();


//    String vin;
//    String carIdNumber;
//    String rentNumber;
//    OffsetDateTime receivedDateTime;
//    OrderStatus orderStatus;
//    Customer customer;
//    Employee employee;
}
