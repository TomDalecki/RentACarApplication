package pl.TomDal.RentACarApplication.controllers.rest;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CarsToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.services.CarToRentService;

@RestController
@RequestMapping(CarToRentRestController.CARTORENT)
@AllArgsConstructor
public class CarToRentRestController {

    public static final String CARTORENT = "/carToRent";
    public static final String CARTORENT_IDNumber = "{idNumber}";

    private CarToRentService carToRentService;
    private CarToRentMapper carToRentMapper;

    @GetMapping
    CarsToRentDTO carsToRentList(){
        return CarsToRentDTO.of(carToRentService.findAvailableCars().stream().map(carToRentMapper::mapToDTO).toList());
    }

    @GetMapping(value = CARTORENT_IDNumber)
    CarToRentDTO carDetails(@PathVariable String idNumber){
        return carToRentMapper.mapToDTO(carToRentService.findByCarIdNumber(idNumber));
    }

}
