package pl.TomDal.RentACarApplication.repository;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;
import pl.TomDal.RentACarApplication.entity.enums.CarType;

import java.util.List;
import java.util.NoSuchElementException;

import static pl.TomDal.RentACarApplication.util.TestDataFixtures.*;

@ActiveProfiles("test")
@DataJpaTest
@ComponentScan(basePackages = "pl.TomDal.RentACarApplication")
@TestPropertySource(locations = "classpath:application-test.yml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CarToRentRepositoryTest {

    private CarToRentRepository carToRentRepository;

    @BeforeEach
    void setUp(){
        carToRentRepository.saveCar(testCar1());
        carToRentRepository.saveCar(testCar2());
        carToRentRepository.saveCar(testCar3());
    }

    @Test
    void thatCarToRentCanBeSavedCorrectly(){
       //given
       //when
        List<CarToRent> carsToRentFound = carToRentRepository.findAllCars();

        //then
        Assertions.assertThat(carsToRentFound.size()).isEqualTo(3);

        carToRentRepository.deleteCar(testCar1());
    }

    @Test
    void thatCarToRentCanBeDeleteCorrectly(){
        //given
        carToRentRepository.deleteCar(testCar1());
        carToRentRepository.deleteCar(testCar2());
        carToRentRepository.deleteCar(testCar3());

        //when
        List<CarToRent> carsToRentFound = carToRentRepository.findAllCars();

        //then
        Assertions.assertThat(carsToRentFound.size()).isEqualTo(0);
    }

    @Test
    void thatCarStatusIsChangedCorrectly(){
        //given
        List<CarToRent> carsToRent = carToRentRepository.findAllCars();
        Integer carToRentId = carsToRent.get(0).getCarToRentId();
        CarStatus carStatus = CarStatus.TECHNICAL_ISSUE;

        //when
        carToRentRepository.changeCarStatusByCarId(carToRentId, carStatus);

        //then
        CarToRent carToRent = carToRentRepository.findCarToRentByCarId(carToRentId)
                .orElseThrow(() -> new NoSuchElementException("Car not found"));
        Assertions.assertThat(carToRent.getCarStatus()).isEqualTo(CarStatus.TECHNICAL_ISSUE);
    }

    @Test
    void thatThrowExceptionWhenCarNotFound() {
        // Given
        Integer nonExistentCarId = 999;

        // When, Then
        Assertions.assertThatThrownBy(() -> {
            carToRentRepository.findCarToRentByCarId(nonExistentCarId).orElseThrow();
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void thatFindCarsByStatusCorrectly(){
        //given
        CarStatus carStatus1 = CarStatus.TO_RENT;
        CarStatus carStatus2 = CarStatus.DISABLED_BY_TECH_INSP;

        // given when
        List<CarToRent> resultWithExample = carToRentRepository.findCarsToRentByCarStatus(carStatus1);
        List<CarToRent> resultWithoutExample = carToRentRepository.findCarsToRentByCarStatus(carStatus2);

        //then
        Assertions.assertThat(resultWithExample.size()).isEqualTo(3);
        Assertions.assertThat(resultWithoutExample.size()).isEqualTo(0);
    }

    @Test
    void findAvailableCarsByCarType() {
        //given
        CarType carType = CarType.Sedan;

        //when
        List<CarToRent> result = carToRentRepository.findAvailableCarsByCarType(carType);

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}