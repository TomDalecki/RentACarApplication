package pl.TomDal.RentACarApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.services.EmployeeService;

import java.time.LocalDate;

import static pl.TomDal.RentACarApplication.entity.enums.CarType.Sedan;

@SpringBootApplication
public class RentACarApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RentACarApplication.class, args);

        CarToRentService carToRentService = context.getBean(CarToRentService.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);


//        employeeService.findOpenRentalOrdersByEmail("piotr@com.pl");

//        CarToRent car1 = CarToRent.builder()
//                .vin("5FT7X2B60FEA74012")
//                .carIdNumber("SC12347")
//                .carType(Sedan)
//                .brand("Fiat")
//                .model("Punto")
//                .year(2014)
//                .color("red")
//                .carStatus(CarStatus.DISABLED_BY_TECH_INSP)
//                .build();

//        carToRentService.addCar(car1);

//        carToRentService.findAvailableCarsByStartEndDate(
//                LocalDate.of(2023, 7,23), LocalDate.of(2023,7,25));
//        System.out.println("------------------------------");

//        carToRentService.findAvailableCars();
//        System.out.println("------------------------------");
//        carToRentService.findAvailableCarsByCarType(SUV);
//        System.out.println("------------------------------");
//        carToRentService.findByCarIdNumber("SK12345");
//        System.out.println("------------------------------");
//        carToRentService.findByVin("1G1PE5S97B7239380");
//        System.out.println("------------------------------");
    }
}
