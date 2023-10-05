package pl.TomDal.RentACarApplication.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.services.CarToRentService;

import java.util.Optional;

@RestController
@RequestMapping(AdminRestController.ADMIN)
@AllArgsConstructor
public class AdminRestController {

    public static final String ADMIN = "/api";
    public static final String ADMIN_DELETE_CAR = "/deleteCar/{vin}";
    private CarToRentService carToRentService;

    @DeleteMapping(value = ADMIN_DELETE_CAR)
    public ResponseEntity<?> deleteCarToRent(@PathVariable String vin) {

        Optional<CarToRent> existingCar = carToRentService.findByVin(vin);

        if (existingCar.isPresent()) {
            carToRentService.deleteCarByVin(existingCar.get().getVin());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
