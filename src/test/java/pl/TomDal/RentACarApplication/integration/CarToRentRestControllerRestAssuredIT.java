package pl.TomDal.RentACarApplication.integration;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CarsToRentDTO;
import pl.TomDal.RentACarApplication.controllers.rest.CarToRentRestController;
import pl.TomDal.RentACarApplication.integration.configuration.RestAssuredIntegrationTestBase;
import pl.TomDal.RentACarApplication.integration.support.CarToRentRestControllerTestSupport;
import pl.TomDal.RentACarApplication.repository.jpa.CarToRentJpaRepository;
import pl.TomDal.RentACarApplication.util.TestDtoDataFixtures;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;


public class CarToRentRestControllerRestAssuredIT
        extends RestAssuredIntegrationTestBase
        implements CarToRentRestControllerTestSupport {

    @Autowired
    private CarToRentJpaRepository carToRentJpaRepository;

    @AfterEach
    public void after() {
        carToRentJpaRepository.deleteAll();
    }


    @Test
    void thatAllCarsToRentCanBeRetrievedCorrectly() {
        //given
        CarToRentDTO carToRentDTO1 = TestDtoDataFixtures.testCarDTO1();
        CarToRentRestController.CarToRentRestDTO carToRentRestDTO1 = new CarToRentRestController.CarToRentRestDTO();
        carToRentRestDTO1.setCarToRentDTO(carToRentDTO1);
        carToRentRestDTO1.setTechnicalInspectionDate(LocalDate.now());

        CarToRentDTO carToRentDTO2 = TestDtoDataFixtures.testCarDTO2();
        CarToRentRestController.CarToRentRestDTO carToRentRestDTO2 = new CarToRentRestController.CarToRentRestDTO();
        carToRentRestDTO2.setCarToRentDTO(carToRentDTO2);
        carToRentRestDTO2.setTechnicalInspectionDate(LocalDate.now());

        CarToRentDTO carToRentDTO3 = TestDtoDataFixtures.testCarDTO3();
        CarToRentRestController.CarToRentRestDTO carToRentRestDTO3 = new CarToRentRestController.CarToRentRestDTO();
        carToRentRestDTO3.setCarToRentDTO(carToRentDTO3);
        carToRentRestDTO3.setTechnicalInspectionDate(LocalDate.now());

        //when
        addCarToRent(carToRentRestDTO1);
        addCarToRent(carToRentRestDTO2);
        addCarToRent(carToRentRestDTO3);

        CarsToRentDTO carsToRentDTO = allCarsList();

        //then
        assertThat(carsToRentDTO.getCarsToRent())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("carToRentId", "carStatus")
                .containsExactlyInAnyOrder(carToRentDTO1, carToRentDTO2, carToRentDTO3);
    }

    @Test
    void thatCarToRentCanBeAddCorrectly(){
        //given
        CarToRentDTO carToRentDTO1 = TestDtoDataFixtures.testCarDTO1();
        CarToRentRestController.CarToRentRestDTO carToRentRestDTO1 = new CarToRentRestController.CarToRentRestDTO();
        carToRentRestDTO1.setCarToRentDTO(carToRentDTO1);
        carToRentRestDTO1.setTechnicalInspectionDate(LocalDate.now());

        //when
        ExtractableResponse<Response> response = addCarToRent(carToRentRestDTO1);

        //then
        String responseAsString = response.body().asString();
        assertThat(responseAsString).isEmpty();

        assertThat(response.headers().get("Location").getValue())
                .matches(Pattern.compile("/api/findCar/\\d+"));
    }

//    @Test
//    void thatCreatedCarToRentCanBeRetrievedCorrectly(){
//
//        //given
//        CarToRentDTO carToRentDTO1 = TestDtoDataFixtures.testCarDTO1();
//        CarToRentRestController.CarToRentRestDTO carToRentRestDTO1 = new CarToRentRestController.CarToRentRestDTO();
//        carToRentRestDTO1.setCarToRentDTO(carToRentDTO1);
//        carToRentRestDTO1.setTechnicalInspectionDate(LocalDate.now());
//
//        //when
//        ExtractableResponse<Response> response = addCarToRent(carToRentRestDTO1);
//        String carToRentDetailsPath = response.headers().get("Location").getValue();
//
//
//        CarToRentDTO carToRent = getCarToRent(carToRentDetailsPath);
//
//        //then
//        assertThat(carToRent)
//                .usingRecursiveComparison()
//                .ignoringFields("carToRentId")
//                .isEqualTo(carToRentDTO1);
//
//    }
}
