package pl.TomDal.RentACarApplication.integration.support;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.http.HttpStatus;
import pl.TomDal.RentACarApplication.controllers.rest.AdminRestController;

public interface AdminRestControllerTestSupport {

    RequestSpecification requestSpecification();


    default ExtractableResponse<Response> deleteCarToRent(String vin){
        return requestSpecification()
                .delete(AdminRestController.ADMIN + AdminRestController.ADMIN_DELETE_CAR, vin)
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract();
    }

    default ExtractableResponse<Response> deleteCarToRent_notExistentVin(String vin){
        return requestSpecification()
                .delete(AdminRestController.ADMIN + AdminRestController.ADMIN_DELETE_CAR, vin)
                .then()
                //.statusCode(HttpStatus.NOT_FOUND.value())
                .and()
                .extract();
    }
}
