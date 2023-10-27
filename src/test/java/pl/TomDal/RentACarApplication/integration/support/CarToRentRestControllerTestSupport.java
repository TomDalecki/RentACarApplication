package pl.TomDal.RentACarApplication.integration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.TomDal.RentACarApplication.controllers.dto.CarToRentDTO;
import pl.TomDal.RentACarApplication.controllers.dto.CarsToRentDTO;
import pl.TomDal.RentACarApplication.controllers.rest.CarToRentRestController;
import pl.TomDal.RentACarApplication.entity.enums.CarStatus;

public interface CarToRentRestControllerTestSupport {

    RequestSpecification requestSpecification();


    //ten zapis to całe qlue używania RestAssured, w ten sposób definiujemy, że RestAssured jako klient ma wykonać
    //zapytanie/żądanie get na naszą aplikację, na nasz endpoint, a następnie sprawdzamy, czy odpowiedź jest poprawna
    //oraz przemapować odpowiedź (zwróconego jason'a) na obiekt CarsToRentDTO
    default CarsToRentDTO allCarsList(){
        return requestSpecification()
                .get(CarToRentRestController.CARTORENT)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CarsToRentDTO.class);
    }

    default CarsToRentDTO carsToRentList(CarStatus carStatu){
        return requestSpecification()
                .get(CarToRentRestController.CARTORENT_CAR_STATUS, carStatu)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CarsToRentDTO.class);
    }


    default CarToRentDTO carDetails(String carIdNumber){
        return requestSpecification()
                .get(CarToRentRestController.CARTORENT_CAR_ID_NUMBER, carIdNumber)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract()
                .as(CarToRentDTO.class);
    }

    default ExtractableResponse<Response> addCarToRent(CarToRentRestController.CarToRentRestDTO carToRentRestDTO){
        return requestSpecification()
                .body(carToRentRestDTO)
                .post(CarToRentRestController.CARTORENT)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .and()
                .extract();
    }
}
