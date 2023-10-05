package pl.TomDal.RentACarApplication.controllers.rest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.TomDal.RentACarApplication.domain.CarToRent;
import pl.TomDal.RentACarApplication.services.CarToRentService;
import pl.TomDal.RentACarApplication.util.TestDataFixtures;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminRestControllerUnitTest {
    @Mock
    private CarToRentService carToRentService;

    @InjectMocks
    private AdminRestController adminRestController;

    @Test
    void thatDeletingCarByVinWorksCorrectly_CarExist() {

        //given
        CarToRent carToRent = TestDataFixtures.testCar1();
        String vin = carToRent.getVin();
        when(carToRentService.findByVin(vin)).thenReturn(Optional.of(carToRent));

        //when
        ResponseEntity<?> response = adminRestController.deleteCarToRent(vin);

        //then
        verify(carToRentService, times(1)).deleteCarByVin(vin);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void thatDeletingCarByVinWorksCorrectly_CarDoesNotExist() {

        //given
        String vin = "someNotExistingVIN";
        when(carToRentService.findByVin(vin)).thenReturn(Optional.empty());

        //when
        ResponseEntity<?> response = adminRestController.deleteCarToRent(vin);

        //then
        verify(carToRentService, times(0)).deleteCarByVin(vin);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


}