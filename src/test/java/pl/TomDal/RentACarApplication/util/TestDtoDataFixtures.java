package pl.TomDal.RentACarApplication.util;

import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CustomerDTO;
import pl.TomDal.RentACarApplication.controllers.dto.EmployeeDTO;
import pl.TomDal.RentACarApplication.controllers.dto.RentalOrderDTO;
import pl.TomDal.RentACarApplication.domain.*;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TestDtoDataFixtures {

    public static CustomerDTO someCustomerDTO1() {
        return CustomerDTO.builder()
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

    public static CustomerDTO someCustomerDTO2() {
        return CustomerDTO.builder()
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

    public static CustomerDTO someCustomerDTO3() {
        return CustomerDTO.builder()
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

    public static CarToRentDTO testCarDTO1() {
        return CarToRentDTO.builder()
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

    public static CarToRentDTO testCarDTO2() {
        return CarToRentDTO.builder()
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

    public static CarToRentDTO testCarDTO3() {
        return CarToRentDTO.builder()
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

    public static EmployeeDTO testEmployeeDTO1() {
        return EmployeeDTO.builder()
                .name("Andrzej")
                .surname("Pracownik1")
                .email("testEmpl1@pl")
                .pesel("80100556845")
                .build();
    }

    public static EmployeeDTO testEmployeeDTO2() {
        return EmployeeDTO.builder()
                .name("Robert")
                .surname("Pracownik2")
                .email("testEmpl2@pl")
                .pesel("74012556365")
                .build();
    }

    public static EmployeeDTO testEmployeeDTO3() {
        return EmployeeDTO.builder()
                .name("Wojtek")
                .surname("Pracownik3")
                .email("testEmpl3@pl")
                .pesel("650407587412")
                .build();
    }

    public static RentalOrderDTO testRentalOrderDTO1() {
        return RentalOrderDTO.builder()
                .rentNumber("123456789")
                .receivedDateTime(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .rentalStartDate(LocalDate.of(2023, 1, 1))
                .rentalEndDate(LocalDate.of(2023, 1, 10))
                .totalPrice(BigDecimal.valueOf(250.00))
                .orderStatus(OrderStatus.NEW_ORDER)
                .build();
    }

    public static RentalOrderDTO testRentalOrderDTO2() {
        return RentalOrderDTO.builder()
                .rentNumber("234567891")
                .receivedDateTime(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .rentalStartDate(LocalDate.of(2023, 1, 1))
                .rentalEndDate(LocalDate.of(2023, 1, 10))
                .totalPrice(BigDecimal.valueOf(250.00))
                .orderStatus(OrderStatus.NEW_ORDER)
                .build();
    }

    public static RentalOrderDTO testRentalOrderDTO3() {
        return RentalOrderDTO.builder()
                .rentNumber("345678921")
                .receivedDateTime(OffsetDateTime.of(2023, 7, 15, 0, 0, 0, 0, ZoneOffset.ofHours(0)))
                .rentalStartDate(LocalDate.of(2023, 1, 1))
                .rentalEndDate(LocalDate.of(2023, 1, 10))
                .totalPrice(BigDecimal.valueOf(250.00))
                .orderStatus(OrderStatus.NEW_ORDER)
                .build();
    }

}
