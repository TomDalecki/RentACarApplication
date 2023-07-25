package pl.TomDal.RentACarApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.TomDal.RentACarApplication.services.CarToRentService;

import static pl.TomDal.RentACarApplication.entity.enums.CarType.*;

@SpringBootApplication
public class RentACarApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(RentACarApplication.class, args);

        CarToRentService carToRentService = context.getBean(CarToRentService.class);

        carToRentService.findAllCars();
        System.out.println("------------------------------");
        carToRentService.findAvailableCars();
        System.out.println("------------------------------");
        carToRentService.findAvailableCarsByCarType(SUV);
        System.out.println("------------------------------");
        carToRentService.findByCarIdNumber("SK12345");
        System.out.println("------------------------------");
        carToRentService.findByVin("1G1PE5S97B7239380");
        System.out.println("------------------------------");
    }
}
