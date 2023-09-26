package pl.TomDal.RentACarApplication.controllers.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CarsToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.services.CarToRentService;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(CarToRentRestController.CARTORENT)
@AllArgsConstructor
public class CarToRentRestController {

    public static final String CARTORENT = "/api";
    public static final String CARTORENT_CAR_ID_NUMBER = "/findCar/{carIdNumber}";
    public static final String CARTORENT_CAR_ID_NUMBER_RESULT = "/findCar/%s";
    public static final String CARTORENT_CAR_STATUS = "/findWithStatus/{carStatus}";

    private CarToRentService carToRentService;
    private CarToRentMapper carToRentMapper;

    @GetMapping
    CarsToRentDTO allCarsList(){
        return CarsToRentDTO.of(carToRentService.findAllCars()
                .stream().map(carToRentMapper::mapToDTO).toList());
    }

    @GetMapping(value = CARTORENT_CAR_STATUS)
    CarsToRentDTO carsToRentList(@PathVariable CarStatus carStatus){
        return CarsToRentDTO.of(carToRentService.findCarsToRentByCarStatus(carStatus)
                .stream().map(carToRentMapper::mapToDTO).toList());
    }

    @GetMapping(value = CARTORENT_CAR_ID_NUMBER)
    CarToRentDTO carDetails(@PathVariable String carIdNumber){
        return carToRentMapper.mapToDTO(carToRentService.findByCarIdNumber(carIdNumber));
    }

    @Getter
    @Setter
    public static class CarToRentRestDTO{
        private CarToRentDTO carToRentDTO;
        private LocalDate technicalInspectionDate;
    }

    @PostMapping
    public ResponseEntity<CarToRentDTO> addCarToRent(
            @RequestBody CarToRentRestDTO carToRentRestDTO
    ){
        CarToRentDTO carToSave = carToRentRestDTO.getCarToRentDTO().withCarStatus(CarStatus.DISABLED_BY_INSURANCE);

        CarToRentDTO newCar = carToRentService.saveCar(carToSave, carToRentRestDTO.getTechnicalInspectionDate());

        return ResponseEntity
                .created(URI.create(CARTORENT + CARTORENT_CAR_ID_NUMBER_RESULT.formatted(newCar.getCarToRentId())))
                .build();
    }



}
