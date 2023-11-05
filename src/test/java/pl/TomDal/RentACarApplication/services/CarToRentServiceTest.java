package pl.TomDal.RentACarApplication.services;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.mapper.CarToRentMapper;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;
import pl.TomDal.RentACarApplication.services.dao.CarToRentDAO;
import pl.TomDal.RentACarApplication.services.dao.TechnicalInspectionDAO;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarToRentServiceTest {
    @InjectMocks
    CarToRentService carToRentService;
    @Mock
    private CarToRentDAO carToRentDAO;
    @Mock
    private TechnicalInspectionDAO technicalInspectionDAO;
    @Mock
    private CarToRentMapper carToRentMapper;

    @Test
    void thatFindingAllCarsWorksCorrectly() {
        //given
        List<CarToRent> expectedCars = new ArrayList<>();
        expectedCars.add(TestDataFixtures.testCar1());
        expectedCars.add(TestDataFixtures.testCar2());
        expectedCars.add(TestDataFixtures.testCar3());

        when(carToRentDAO.findAllCars()).thenReturn(expectedCars);

        //when
        List<CarToRent> result = carToRentService.findAllCars();

        //then
        assertEquals(expectedCars.size(), result.size());

        verify(carToRentDAO, Mockito.times(1)).findAllCars();
    }

    @Test
    void thatFindingAvailableCarsWorksCorrectly() {
        //given
        List<CarToRent> expectedCars = new ArrayList<>();
        expectedCars.add(TestDataFixtures.testCar1());
        expectedCars.add(TestDataFixtures.testCar2());
        expectedCars.add(TestDataFixtures.testCar3());

        when(carToRentDAO.findCarsAvailableToRent()).thenReturn(expectedCars);

        //when
        List<CarToRent> result = carToRentService.findAvailableCars();

        //then
        assertEquals(expectedCars.size(), result.size());

        verify(carToRentDAO, Mockito.times(1)).findCarsAvailableToRent();
    }

    @Test
    void thatFindingCarsToRentByCarStatusWorksCorrectly() {
        //given
        List<CarToRent> expectedCars = new ArrayList<>();
        expectedCars.add(TestDataFixtures.testCar1());
        expectedCars.add(TestDataFixtures.testCar2());

        when(carToRentDAO.findCarsToRentByCarStatus(CarStatus.TO_RENT)).thenReturn(expectedCars);

        //when
        List<CarToRent> result = carToRentService.findCarsToRentByCarStatus(CarStatus.TO_RENT);

        //then
        assertEquals(expectedCars.size(), result.size());

        verify(carToRentDAO, Mockito.times(1)).findCarsToRentByCarStatus(CarStatus.TO_RENT);
    }

    @Test
    void thatFindingAvailableCarsByCarTypeWorksCorrectly() {
        //given
        List<CarToRent> expectedCars = new ArrayList<>();
        expectedCars.add(TestDataFixtures.testCar1());
        expectedCars.add(TestDataFixtures.testCar3());

        when(carToRentDAO.findAvailableCarsByCarType(CarType.Sedan)).thenReturn(expectedCars);

        //when
        List<CarToRent> result = carToRentService.findAvailableCarsByCarType(CarType.Sedan);

        //then
        assertEquals(expectedCars.size(), result.size());

        verify(carToRentDAO, Mockito.times(1)).findAvailableCarsByCarType(CarType.Sedan);
    }

    @Test
    void thatFindingAvailableCarsByStartEndDateWorksCorrectly() {
        //given
        List<CarToRent> expectedCars = new ArrayList<>();
        expectedCars.add(TestDataFixtures.testCar1());

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(carToRentDAO.findAvailableCarsByStartEndDate(startDate, endDate)).thenReturn(expectedCars);

        //when
        List<CarToRent> result = carToRentService.findAvailableCarsByStartEndDate(startDate, endDate);

        //then
        assertEquals(expectedCars.size(), result.size());

        verify(carToRentDAO, Mockito.times(1))
                .findAvailableCarsByStartEndDate(startDate, endDate);

    }

    @Test
    void thatFindingByCarIdNumberWorksCorrectly() {
        //given
        CarToRent expectedCar = TestDataFixtures.testCar1();
        String carIdNumber = "SL6883G";

        when(carToRentDAO.findByCarIdNumber(carIdNumber)).thenReturn(Optional.ofNullable(expectedCar));

        //when
        CarToRent result = carToRentService.findByCarIdNumber(carIdNumber);

        //then
        assert expectedCar != null;
        assertEquals(expectedCar.getBrand(), result.getBrand());

        verify(carToRentDAO, Mockito.times(1)).findByCarIdNumber(carIdNumber);
    }

    @Test
    void thatFindingByNotExistingCarIdNumberThrowsException() {
        //given
        String carIdNumber = "notExistingCarIdNumber";
        String expectedMessage = "Could not find the car with IdNumber: [%s]".formatted(carIdNumber);

        //when
        when(carToRentDAO.findByCarIdNumber(carIdNumber)).thenReturn(Optional.empty());

        //then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> carToRentService.findByCarIdNumber(carIdNumber));

        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);

        verify(carToRentDAO, Mockito.times(1)).findByCarIdNumber(carIdNumber);
    }

    @Test
    void thatFindingByVinWorksCorrectly() {
        //given
        CarToRent expectedCar = TestDataFixtures.testCar1();
        String vin = expectedCar.getVin();

        when(carToRentDAO.findByVin(vin)).thenReturn(Optional.of(expectedCar));

        //when
        CarToRent result = carToRentService.findByVin(vin).orElseThrow();

        //then
        assertEquals(expectedCar.getBrand(), result.getBrand());

        verify(carToRentDAO, Mockito.times(1)).findByVin(vin);
    }

    @Test
    void thatChangingCarStatusByCarIdWorksCorrectly() {
        //given
        Integer carToRentId = 1;
        CarStatus newCarStatus = CarStatus.DISABLED_BY_INSURANCE;

        doNothing().when(carToRentDAO).changeCarStatusByCarId(anyInt(), any(CarStatus.class));

        //when
        carToRentService.changeCarStatusByCarId(carToRentId, newCarStatus);

        //then
        verify(carToRentDAO).changeCarStatusByCarId(carToRentId, newCarStatus);
    }

    @Test
    void thatSavingCarWorksCorrectly() {
        //given
        CarToRent carToRent = TestDataFixtures.testCar1();
        CarToRentDTO carToRentDTO = TestDtoDataFixtures.testCarDTO1();
        LocalDate technicalInspectionDate = LocalDate.now();

        doNothing().when(carToRentDAO).saveCar(carToRent);
        when(carToRentMapper.mapFromDTO(carToRentDTO)).thenReturn(carToRent);
        when(carToRentDAO.findByVin(carToRentDTO.getVin())).thenReturn(Optional.of(carToRent));
        when(carToRentMapper.mapToDTO(carToRent)).thenReturn(carToRentDTO);

        //when
        CarToRentDTO result = carToRentService.saveCar(carToRentDTO, technicalInspectionDate);

        //then
        assertEquals(carToRentDTO.getVin(), result.getVin());

        verify(carToRentDAO).saveCar(any(CarToRent.class));
        verify(technicalInspectionDAO).saveTechnicalInspection(carToRent.getCarToRentId(), technicalInspectionDate);

        verify(carToRentDAO, times(1)).saveCar(carToRent);
        verify(technicalInspectionDAO, times(1))
                .saveTechnicalInspection(carToRent.getCarToRentId(), technicalInspectionDate);
    }

    @Test
    void thatUpdateTechnicalStatusWorksCorrectly() {
        //given
        Integer carToRentId = 1;
        CarStatus newCarStatus = CarStatus.TO_RENT;

        doNothing().when(carToRentDAO).changeCarStatusByCarId(carToRentId, newCarStatus);

        //when
        carToRentService.updateTechnicalStatus(carToRentId, newCarStatus);

        //then
        verify(carToRentDAO).changeCarStatusByCarId(carToRentId, newCarStatus);
        verify(carToRentDAO, times(1)).changeCarStatusByCarId(carToRentId, newCarStatus);
    }

    @Test
    void thatDeletingCarByVinWorksCorrectly() {
        //given
        String vin = TestDataFixtures.testCar1().getVin();

        doNothing().when(carToRentDAO).deleteCarByVin(vin);

        //when
        carToRentService.deleteCarByVin(vin);

        //then
        verify(carToRentDAO).deleteCarByVin(vin);
        verify(carToRentDAO, times(1)).deleteCarByVin(vin);
    }
}