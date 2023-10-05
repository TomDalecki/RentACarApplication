package pl.TomDal.RentACarApplication.controllers.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CarsToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@WebMvcTest(CarToRentRestController.class)
//@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class CarToRentRestControllerUnitTest {
//    @Autowired
//    private final MockMvc mockMvc;

    @Mock
    private CarToRentService carToRentService;

    @Mock
    private CarToRentMapper carToRentMapper;

    @InjectMocks
    private CarToRentRestController carToRentRestController;

    @Test
    void thatRetrievingAllCarsWorksCorrectly() {
        //given
        CarToRent carToRent = TestDataFixtures.testCar1();
        List<CarToRent> carsToRent = new ArrayList<>();
        CarToRentDTO carToRentDTO = new CarToRentDTO();
        carsToRent.add(carToRent);

        when(carToRentService.findAllCars()).thenReturn(carsToRent);
        when(carToRentMapper.mapToDTO(carToRent)).thenReturn(carToRentDTO);

        //when
        CarsToRentDTO response = carToRentRestController.allCarsList();

        //then
        assertEquals(1, response.getCarsToRent().size());
    }

    @Test
    void thatRetrievingCarsToRentByCarStatusWorksCorrectly() {
        //given
        CarStatus carStatus = CarStatus.TO_RENT;
        CarToRent carToRent = TestDataFixtures.testCar1();
        List<CarToRent> carsToRent = new ArrayList<>();
        CarToRentDTO carToRentDTO = new CarToRentDTO();
        carToRentDTO.setCarStatus(carStatus);
        carsToRent.add(carToRent);

        when(carToRentService.findCarsToRentByCarStatus(carStatus)).thenReturn(carsToRent);
        when(carToRentMapper.mapToDTO(carToRent)).thenReturn(carToRentDTO);

        //when
        CarsToRentDTO response = carToRentRestController.carsToRentList(carStatus);

        //then
        assertEquals(1, response.getCarsToRent().size());
    }

    @Test
    void thatRetrievingCarDetailsWorksCorrectly() {
        //given
        String carIdNumber = "SL6883G";
        CarToRent carToRent = TestDataFixtures.testCar1();
        CarToRentDTO carToRentDTO1 = TestDtoDataFixtures.testCarDTO1();

        when(carToRentService.findByCarIdNumber(carIdNumber)).thenReturn(carToRent);
        when(carToRentMapper.mapToDTO(carToRent)).thenReturn(carToRentDTO1);

        //when
        CarToRentDTO response = carToRentRestController.carDetails(carIdNumber);

        //then
        assertEquals(carIdNumber, response.getCarIdNumber());
    }

    @Test
    void thatSavingNewCarWorksCorrectly() {
        {
            //given
            CarToRentDTO carToRentDTO = TestDtoDataFixtures.testCarDTO1();
            carToRentDTO.setCarStatus(CarStatus.DISABLED_BY_INSURANCE);
            carToRentDTO.setCarIdNumber("123");

            CarToRentRestController.CarToRentRestDTO carToRentRestDTO = new CarToRentRestController.CarToRentRestDTO();
            carToRentRestDTO.setCarToRentDTO(carToRentDTO);

            when(carToRentService.saveCar(carToRentDTO, carToRentRestDTO.getTechnicalInspectionDate())).thenReturn(carToRentDTO);

            //when
            ResponseEntity<CarToRentDTO> responseEntity = carToRentRestController.addCarToRent(carToRentRestDTO);

            //then
            assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        }
    }
}