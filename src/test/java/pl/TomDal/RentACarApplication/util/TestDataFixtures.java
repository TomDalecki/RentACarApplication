package pl.TomDal.RentACarApplication.util;

import org.jetbrains.annotations.NotNull;
import pl.TomDal.RentACarApplication.domain.*;
import pl.TomDal.RentACarApplication.entity.CarInsuranceEntity;
import pl.TomDal.RentACarApplication.entity.enums.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TestDataFixtures {

    public static Customer someCustomer1() {
        return Customer.builder()
                .name("Piotr")
                .surname("Kowalski")
                .phone("+48 852 963 741")
                .email("jankowalski@email.com")
                .address(Address.builder()
                        .country("Poland")
                        .city("Katowice")
                        .postalCode("40-500")
                        .address("Kwiatowa 1")
                        .build())
                .build();
    }

    public static Customer someCustomer2() {
        return Customer.builder()
                .name("Andrzej")
                .surname("Kwiatkowski")
                .phone("+48 777 963 777")
                .email("akwiatkowski@email.com")
                .address(Address.builder()
                        .country("Poland")
                        .city("Chorzów")
                        .postalCode("40-500")
                        .address("Bykowa 67")
                        .build())
                .build();
    }

    public static Customer someCustomer3() {
        return Customer.builder()
                .name("Łukasz")
                .surname("malinowski")
                .phone("+48 543 789 736")
                .email("lmalinowski@email.com")
                .address(Address.builder()
                        .country("Poland")
                        .city("Siemianowice")
                        .postalCode("40-512")
                        .address("Rosomaka 37")
                        .build())
                .build();
    }

    public static CarToRent testCar1() {
        return CarToRent.builder()
                .vin("2GT7X2B60FEA74019")
                .carIdNumber("SL6883G")
                .carType(CarType.Sedan)
                .brand("Opel")
                .model("Vectra")
                .year(2008)
                .color("Black")
                .carStatus(CarStatus.TO_RENT)
                .build();
    }

    public static CarToRent testCar2() {
        return CarToRent.builder()
                .vin("7PT7X2B60FEA74256")
                .carIdNumber("SK6773P")
                .carType(CarType.SUV)
                .brand("Ford")
                .model("Kuga")
                .year(2018)
                .color("White")
                .carStatus(CarStatus.TO_RENT)
                .build();
    }

    public static CarToRent testCar3() {
        return CarToRent.builder()
                .vin("2QW7X2B60FEA72023")
                .carIdNumber("SW1225G")
                .carType(CarType.Sedan)
                .brand("Peugeot")
                .model("206")
                .year(2020)
                .color("Silver")
                .carStatus(CarStatus.TO_RENT)
                .build();
    }

    public static Employee testEmployee1() {
        return Employee.builder()
                .name("Andrzej")
                .surname("Pracownik1")
                .email("testEmpl1@pl")
                .pesel("80100556845")
                .build();
    }

    public static Employee testEmployee2() {
        return Employee.builder()
                .name("Robert")
                .surname("Pracownik2")
                .email("testEmpl2@pl")
                .pesel("74012556365")
                .build();
    }

    public static Employee testEmployee3() {
        return Employee.builder()
                .name("Wojtek")
                .surname("Pracownik3")
                .email("testEmpl3@pl")
                .pesel("650407587412")
                .build();
    }

    public static Price testPrice1() {
        return Price.builder()
                .priceDate(OffsetDateTime.of(2023, 5, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .carType(CarType.Sedan)
                .price(BigDecimal.valueOf(50.00))
                .build();
    }

    public static Price testPrice2() {
        return Price.builder()
                .priceDate(OffsetDateTime.of(2023, 8, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .carType(CarType.Sedan)
                .price(BigDecimal.valueOf(100.00))
                .build();
    }

    public static Price testPrice3() {
        return Price.builder()
                .priceDate(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .carType(CarType.Sedan)
                .price(BigDecimal.valueOf(150.00))
                .build();
    }

    public static Price testPrice4() {
        return Price.builder()
                .priceDate(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .carType(CarType.SUV)
                .price(BigDecimal.valueOf(300.00))
                .build();
    }

    public static RentalOrder testRentalOrder1() {
        return RentalOrder.builder()
                .rentNumber("123456789")
                .receivedDateTime(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .rentalStartDate(LocalDate.of(2023, 1, 1))
                .rentalEndDate(LocalDate.of(2023, 1, 10))
                .totalPrice(BigDecimal.valueOf(250.00))
                .orderStatus(OrderStatus.NEW_ORDER)
                .build();
    }

    public static RentalOrder testRentalOrder2() {
        return RentalOrder.builder()
                .rentNumber("234567891")
                .receivedDateTime(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .rentalStartDate(LocalDate.of(2023, 1, 1))
                .rentalEndDate(LocalDate.of(2023, 1, 10))
                .totalPrice(BigDecimal.valueOf(250.00))
                .orderStatus(OrderStatus.NEW_ORDER)
                .build();
    }

    public static RentalOrder testRentalOrder3() {
        return RentalOrder.builder()
                .rentNumber("345678921")
                .receivedDateTime(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .rentalStartDate(LocalDate.of(2023, 1, 1))
                .rentalEndDate(LocalDate.of(2023, 1, 10))
                .totalPrice(BigDecimal.valueOf(250.00))
                .orderStatus(OrderStatus.NEW_ORDER)
                .build();
    }

    public static TechnicalInspection testTechnicalInspection1() {
        return TechnicalInspection.builder()
                .inspectionExpiryDate(LocalDate.of(2023, 7, 15))
                .build();
    }

    public static CarInsurance testOCInsurance1() {
        return CarInsurance.builder()
                .insuranceCompany(InsuranceCompanies.PZU)
                .insuranceType(InsuranceType.OC)
                .insuranceNumber("testOC")
                .insuranceStartDate(LocalDate.now())
                .insuranceEndDate(LocalDate.now())
                .carToRent(testCar1())
                .build();
    }

    public static CarInsurance testACInsurance1() {
        return CarInsurance.builder()
                .insuranceCompany(InsuranceCompanies.PZU)
                .insuranceType(InsuranceType.AC)
                .insuranceNumber("testAC")
                .insuranceStartDate(LocalDate.now())
                .insuranceEndDate(LocalDate.now())
                .carToRent(testCar1())
                .build();
    }

    public static CarInsuranceEntity testOCInsuranceEntity() {
        return CarInsuranceEntity.builder()
                .insuranceCompany(InsuranceCompanies.PZU)
                .insuranceType(InsuranceType.OC)
                .insuranceNumber("testOC")
                .insuranceStartDate(LocalDate.now())
                .insuranceEndDate(LocalDate.now())
                .build();
    }

    public static CarInsuranceEntity testACInsuranceEntity() {
        return CarInsuranceEntity.builder()
                .insuranceCompany(InsuranceCompanies.PZU)
                .insuranceType(InsuranceType.AC)
                .insuranceNumber("testAC")
                .insuranceStartDate(LocalDate.now())
                .insuranceEndDate(LocalDate.now())
                .build();
    }
}
